package com.example.superproject1.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private String title;
    private String content;
    private String author;
    private Long postId;
}
