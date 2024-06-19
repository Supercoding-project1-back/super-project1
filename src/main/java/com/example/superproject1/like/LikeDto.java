package com.example.superproject1.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LikeDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public class Post {
        private int user_id;
        private int post_id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class MessageResponse {
        private int message;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class LikeResponse {
        private int user_id;
        private int post_id;
    }
}
