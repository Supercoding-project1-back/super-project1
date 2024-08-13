package com.example.superproject1.repository.comment;

import com.example.superproject1.repository.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
