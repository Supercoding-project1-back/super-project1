package com.example.superproject1.web.dto.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LikeDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        private int user_id;
        private int post_id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LikeResponse {
        private int user_id;
        private int post_id;
    }
}
