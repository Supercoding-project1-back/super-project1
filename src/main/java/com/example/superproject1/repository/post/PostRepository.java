package com.example.superproject1.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByTitle(String title);
    @Query("SELECT p FROM Post p JOIN p.user u WHERE u.email = :email")
    Optional<List<Post>> findByUserEmail(String email);
}
