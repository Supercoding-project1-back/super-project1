package com.example.superproject1.web.controller.comment;

import com.example.superproject1.web.dto.comment.CommentCreateRequestDTO;
import com.example.superproject1.web.dto.comment.CommentUpdateRequestDTO;
import com.example.superproject1.web.dto.comment.CommentResponseDTO;
import com.example.superproject1.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //CREATE
    @PostMapping
    public Map<String, String> createComment(@RequestBody CommentCreateRequestDTO request) {
        boolean success = commentService.createComment(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 작성되었습니다." : "댓글 작성에 실패했습니다.");
        return response;
    }

    //READ
    @GetMapping
    public Map<String, List<CommentResponseDTO>> getAllComments() {
        List<CommentResponseDTO> comments = commentService.getAllComments();
        Map<String, List<CommentResponseDTO>> response = new HashMap<>();
        response.put("comments", comments);
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCommentById(@PathVariable Long id) {
        Optional<CommentResponseDTO> comment = commentService.getCommentById(id);
        Map<String, Object> response = new HashMap<>();

        if (comment.isPresent()) {
            response.put("comment", comment.get());
        } else {
            response.put("message", "해당 ID의 댓글을 찾을 수 없습니다.");
        }

        return response;
    }

    //UPDATE
    @PutMapping("/{id}")
    public Map<String, String> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDTO request) {
        boolean success = commentService.updateComment(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 수정되었습니다." : "댓글 수정에 실패했습니다.");
        return response;
    }

    //DELTE
    @DeleteMapping("/{id}")
    public Map<String, String> deleteComment(@PathVariable Long id){
        boolean success = commentService.deleteComment(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 삭제되었습니다." : "댓글 삭제에 실패했습니다.");
        return response;
    }
}