package com.example.superproject1.web.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequestDTO {
    private String title;
    private String content;
    private String author;
}
