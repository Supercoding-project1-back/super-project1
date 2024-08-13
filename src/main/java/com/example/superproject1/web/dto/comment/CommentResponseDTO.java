package com.example.superproject1.web.dto.comment;

import com.example.superproject1.repository.comment.Comment;
import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.title = comment.getTitle();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
    }
}
