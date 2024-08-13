package com.example.superproject1.web.controller.post;

import com.example.superproject1.service.post.PostService;
import com.example.superproject1.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<PostListResponseDTO> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/search")
    public ResponseEntity<PostListResponseDTO> searchEmail(@RequestParam("user_email") String user_email){
        Optional<List<PostResponseDTO>> post = postService.searchEmail(user_email);
        System.out.println(post.toString());
        if(post.isPresent()){
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(post.get());
            return ResponseEntity.ok(postListResponseDTO);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/posts/search_title")
//    public ResponseEntity<List<Post>> searchPosts(@RequestParam("post_title") String post_title){
//        Optional<List<Post>> post = postService.searchPosts(post_title);
//        if(post.isPresent()){
//            return ResponseEntity.ok(post.get());
//        } else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping("/posts")
    public ResponseEntity<PostMsgResponseDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO){
        try {
            postService.createPost(postCreateRequestDTO);
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물이 성공적으로 작성되었습니다.");
            return ResponseEntity.ok(postMsgResponseDTO);
        } catch (Exception e){
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물 작성에 실패하였습니다.");
            return ResponseEntity.internalServerError().body(postMsgResponseDTO);
        }
    }

    @Transactional
    @PutMapping("/posts/{post_id}")
    public ResponseEntity<PostMsgResponseDTO> updatePost(@PathVariable Long post_id, @RequestBody PostUpdateRequestDTO postUpdateRequestDTO){
        try {
            postService.updatePost(post_id, postUpdateRequestDTO);
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물 수정을 성공했습니다.");
            return ResponseEntity.ok(postMsgResponseDTO);
        } catch (Exception e){
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물 수정을 살패했습니다.");
            return ResponseEntity.internalServerError().body(postMsgResponseDTO);
        }
    }

    @Transactional
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<PostMsgResponseDTO> deletePost(@PathVariable Long post_id) {
        try {
            postService.deletePost(post_id);
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물이 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(postMsgResponseDTO);
        } catch (Exception e){
            PostMsgResponseDTO postMsgResponseDTO = new PostMsgResponseDTO("게시물 삭제에 실패했습니다.");
            return ResponseEntity.internalServerError().body(postMsgResponseDTO);
        }
    }
}
