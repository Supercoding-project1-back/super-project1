package com.example.superproject1.web.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostListResponseDTO {
    private List<PostResponseDTO> posts;

    public PostListResponseDTO(List<PostResponseDTO> posts) {
        this.posts = posts;
    }
}
