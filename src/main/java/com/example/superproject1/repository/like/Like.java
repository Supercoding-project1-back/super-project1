package com.example.superproject1.repository.like;

import com.example.superproject1.repository.post.Post;
import com.example.superproject1.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "likes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
