package com.example.superproject1.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private String content;
    private String author;
    private Long postId;
}
