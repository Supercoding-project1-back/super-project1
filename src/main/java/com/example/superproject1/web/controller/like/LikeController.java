package com.example.superproject1.web.controller.like;

import com.example.superproject1.web.dto.like.LikeDto;
import com.example.superproject1.repository.like.Like;
import com.example.superproject1.repository.like.LikeMapper;
import com.example.superproject1.service.like.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final LikeMapper likeMapper;

    // 좋아요 추가 API
    @PostMapping
    public ResponseEntity like(@RequestBody LikeDto.Post post) {
        Like like = likeService.createLike(post.getUser_id(), post.getPost_id());
        LikeDto.LikeResponse response = likeMapper.likeToLikeResponse(like);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 좋아요 삭제 API
    @DeleteMapping("/{like_id}")
    public ResponseEntity deleteLike(@PathVariable Long like_id) {
        likeService.deleteLike(like_id);
        LikeDto.MessageResponse response = likeMapper.messageToMessageResponse("좋아요가 성공적으로 삭제되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
