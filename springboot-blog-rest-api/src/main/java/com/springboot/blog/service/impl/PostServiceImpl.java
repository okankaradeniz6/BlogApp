package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize) {
        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object

        List<Post> postList = posts.getContent();
        List<PostDto> postDtoList =postList.stream().map(
                post -> modelMapper.map(post, PostDto.class)
        )
                .collect(Collectors.toList());

        PostResponse postResponse = PostResponse.builder()
                .content(postDtoList)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
        return postResponse;
    }

    @Override
    public PostDto getById(Long id) {
        Post postById = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id)
        );
        return modelMapper.map(postById, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        //getPostById from the Database
        Post postById = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id)
        );
        postById = modelMapper.map(postDto, Post.class);
        postById.setId(id);
        Post updatedPost = postRepository.save(postById);

        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long id) {
        Post postById = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id)
        );
        postRepository.delete(postById);
    }
}
