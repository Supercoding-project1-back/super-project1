package com.example.superproject1.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDTO {
    private String title;
    private String content;
}
