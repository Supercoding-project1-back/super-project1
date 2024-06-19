package com.example.superproject1.like;

import com.example.superproject1.post.Post;
import com.example.superproject1.post.service.PostService;
import com.example.superproject1.user.User;
import com.example.superproject1.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    // like 생성
    public Like createLike(long userId, long postId) {
        // 이미 like 버튼을 눌렸는지 확인
        Post post = postService.findById(postId); // postId 조회
        User user = userService.findById(userId); // userId 조회
        Optional<Like> optionalLike = likeRepository.findByPostAndUser(post, user);

        if(optionalLike.isPresent()) { // 이미 like 버튼을 누른 경우
            return optionalLike.get();
        } else { // like 버튼을 안누른 경우
            return likeRepository.save(Like.builder()
                    .post(post)
                    .user(user)
                    .build()
            );
        }
    }

    // like 삭제
    public void deleteLike(long likeId) {
        Like like = findById(likeId);
        likeRepository.delete(like);
    }

    // likeId로 like를 찾아서 반환
    public Like findById(long likePk) {
        return likeRepository.findById(likePk)
                .orElseThrow(() -> new RuntimeException("해당하는 Id가 없습니다."));
    }
}
