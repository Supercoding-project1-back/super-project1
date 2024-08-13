package com.example.superproject1.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequest {
    private String title;
    private String content;
}
