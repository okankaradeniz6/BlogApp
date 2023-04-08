package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPost(int pageNo, int pageSize);
    PostDto getById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePost(Long id);
}
