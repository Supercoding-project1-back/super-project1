package com.example.superproject1.web.dto.comment;

import lombok.Data;

@Data
public class CommentUpdateRequestDTO {
    private String title;
    private String content;
}