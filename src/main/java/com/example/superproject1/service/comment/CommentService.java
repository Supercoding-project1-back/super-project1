package com.example.superproject1.service.comment;

import com.example.superproject1.web.dto.comment.CommentCreateRequestDTO;
import com.example.superproject1.web.dto.comment.CommentResponseDTO;
import com.example.superproject1.web.dto.comment.CommentUpdateRequestDTO;
import com.example.superproject1.repository.post.Post;
import com.example.superproject1.repository.post.PostRepository;
import com.example.superproject1.repository.comment.Comment;
import com.example.superproject1.repository.comment.CommentRepository;
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
    public Boolean createComment(CommentCreateRequestDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElse(null);

        if (post == null) {
            return false;
        }

        Comment comment = buildCommentFromRequest(request, post);
        commentRepository.save(comment);
        return true;
    }

    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CommentResponseDTO> getCommentById(Long id) {
        return commentRepository.findById(id).map(CommentResponseDTO::new);
    }

    @Transactional
    public Boolean updateComment(Long id, CommentUpdateRequestDTO request) {
        return commentRepository.findById(id)
                .map(comment -> updateAndSaveComment(comment, request))
                .orElse(false);
    }

    @Transactional
    public Boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Comment buildCommentFromRequest(CommentCreateRequestDTO request, Post post) {
        return Comment.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .post(post)
                .build();
    }

    private boolean updateAndSaveComment(Comment comment, CommentUpdateRequestDTO request) {
        comment.setTitle(request.getTitle());
        comment.setContent(request.getContent());
        commentRepository.save(comment);
        return true;
    }
}