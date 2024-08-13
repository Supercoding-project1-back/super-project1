package com.example.superproject1.web.controller.comment;

import com.example.superproject1.web.dto.comment.CommentCreateRequest;
import com.example.superproject1.web.dto.comment.CommentUpdateRequest;
import com.example.superproject1.web.dto.comment.ResponseDTO;
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

    @GetMapping
    public Map<String, List<ResponseDTO>> getAllComments() {
        List<ResponseDTO> comments = commentService.getAllComments();
        Map<String, List<ResponseDTO>> response = new HashMap<>();
        response.put("comments", comments);
        return response;
    }

    @GetMapping("/{id}")
    public Optional<ResponseDTO> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public Map<String, String> createComment(@RequestBody CommentCreateRequest request) {
        boolean success = commentService.createComment(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 작성되었습니다." : "댓글 작성에 실패했습니다.");
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, String> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        boolean success = commentService.updateComment(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 수정되었습니다." : "댓글 수정에 실패했습니다.");
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteComment(@PathVariable Long id) {
        boolean success = commentService.deleteComment(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "댓글이 성공적으로 삭제되었습니다." : "댓글 삭제에 실패했습니다.");
        return response;
    }
}