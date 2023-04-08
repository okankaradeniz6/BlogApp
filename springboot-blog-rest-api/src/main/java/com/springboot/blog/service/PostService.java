package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();
    PostDto getById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePost(Long id);
}
