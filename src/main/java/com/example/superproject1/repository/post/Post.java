package com.example.superproject1.repository.post;

import com.example.superproject1.repository.comment.Comment;
import com.example.superproject1.repository.like.Like;
import com.example.superproject1.repository.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String Author;
    private String content;

}
