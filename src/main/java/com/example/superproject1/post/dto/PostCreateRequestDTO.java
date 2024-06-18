package com.example.superproject1.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequestDTO {
    private String title;
    private String content;
    private String author;
}
