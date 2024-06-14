package com.example.superproject1.like;

import com.example.superproject1.post.Post;
import com.example.superproject1.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "likes")
@Getter
@Setter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
