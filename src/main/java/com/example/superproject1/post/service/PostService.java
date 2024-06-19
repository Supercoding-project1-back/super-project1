package com.example.superproject1.post.service;

import com.example.superproject1.post.Post;
import com.example.superproject1.post.dto.PostCreateRequestDTO;
import com.example.superproject1.post.dto.PostListResponseDTO;
import com.example.superproject1.post.dto.PostResponseDTO;
import com.example.superproject1.post.dto.PostUpdateRequestDTO;
import com.example.superproject1.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostListResponseDTO getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOS = posts.stream().map(post -> {
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            postResponseDTO.setId(post.getId());
            postResponseDTO.setTitle(post.getTitle());
            postResponseDTO.setAuthor(post.getAuthor());
            postResponseDTO.setContent(post.getContent());
            return postResponseDTO;
        }).collect(Collectors.toList());
        return new PostListResponseDTO(postResponseDTOS);
    }

    public Post createPost(PostCreateRequestDTO postCreateRequestDTO){
        Post post = new Post();
        post.setTitle(postCreateRequestDTO.getTitle());
        post.setContent(postCreateRequestDTO.getContent());
        post.setAuthor(postCreateRequestDTO.getAuthor());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostUpdateRequestDTO postUpdateRequestDTO){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("해당하는 Id가 없습니다."));
        post.setTitle(postUpdateRequestDTO.getTitle());
        post.setContent(postUpdateRequestDTO.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id){
        if (!postRepository.existsById(id)){
            throw new RuntimeException();
        }
        postRepository.deleteById(id);
    }

    public Optional<List<Post>> searchPosts(String post_Title) {
        Optional<List<Post>> posts = postRepository.findByTitle(post_Title);
        return posts;
    }

    public Optional<List<PostResponseDTO>> searchEmail(String user_email) {
        Optional<List<Post>> posts = postRepository.findByUserEmail(user_email);
        return posts.map(postList -> postList.stream().map(post -> {
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            postResponseDTO.setId(post.getId());
            postResponseDTO.setTitle(post.getTitle());
            postResponseDTO.setContent(post.getContent());
            postResponseDTO.setAuthor(post.getAuthor());
            return postResponseDTO;
        }).collect(Collectors.toList()));
    }

    // postId로 post 찾기
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당하는 Id가 없습니다."));
    }
}
