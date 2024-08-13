package com.example.superproject1.repository.like;

import com.example.superproject1.repository.post.Post;
import com.example.superproject1.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);

}
