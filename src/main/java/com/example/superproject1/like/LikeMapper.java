package com.example.superproject1.like;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "user_id", source = "user.id")
    @Mapping(target = "post_id", source = "post.id")
    LikeDto.LikeResponse likeToLikeResponse(Like like);

    LikeDto.MessageResponse messageToMessageResponse(String message);
}
