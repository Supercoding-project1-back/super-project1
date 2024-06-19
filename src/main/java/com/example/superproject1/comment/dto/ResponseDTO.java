package com.example.superproject1.comment.dto;

import com.example.superproject1.comment.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Long postId;
    private String createdAt;

    public ResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.title = comment.getTitle();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
        this.postId = comment.getPost().getId();
        this.createdAt = comment.getCreatedAt() != null ? comment.getCreatedAt().toString() : null;
    }
}
