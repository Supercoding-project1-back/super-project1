package com.example.superproject1.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostListResponseDTO {
    private List<PostResponseDTO> posts;

    public PostListResponseDTO(List<PostResponseDTO> posts) {
        this.posts = posts;
    }
}
