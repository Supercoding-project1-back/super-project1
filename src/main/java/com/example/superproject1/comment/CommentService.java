package com.example.superproject1.comment;

import com.example.superproject1.comment.dto.ResponseDTO;
import com.example.superproject1.comment.dto.CommentCreateRequest;
import com.example.superproject1.comment.dto.CommentUpdateRequest;
import com.example.superproject1.post.Post;
import com.example.superproject1.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Boolean createComment(CommentCreateRequest request) {
        try {
            Optional<Post> optionalPost = postRepository.findById(request.getPostId());
            if (!optionalPost.isPresent()) {
                throw new RuntimeException("게시글을 찾을 수 없습니다.");
            }

            Post post = optionalPost.get();

            Comment comment = Comment.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .author(request.getAuthor())
                    .post(post)
                    .build();

            commentRepository.save(comment);
            return true;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ResponseDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ResponseDTO> getCommentById(Long id) {
        return commentRepository.findById(id).map(ResponseDTO::new);
    }

    @Transactional
    public Boolean updateComment(Long id, CommentUpdateRequest request) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setTitle(request.getTitle());
            comment.setContent(request.getContent());
            commentRepository.save(comment);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }
}
