package com.example.superproject1.web.dto.comment;

import lombok.Data;

@Data
public class CommentCreateRequestDTO {
    private String title;
    private String content;
    private String author;
    private Long postId;
}