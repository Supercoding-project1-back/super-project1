package com.example.superproject1.web.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate createAt;
}
