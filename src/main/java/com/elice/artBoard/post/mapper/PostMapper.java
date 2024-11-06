package com.elice.artBoard.post.mapper;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.entity.PostPostDto;
import com.elice.artBoard.post.entity.PostResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    //PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post postPostDtoToPost(PostPostDto postPostDto);
    PostResponseDto postPostToPostResponseDto(Post post);
}
