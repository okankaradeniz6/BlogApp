package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto savedPostDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ){

        return postService.getAllPost(pageNo, pageSize);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id){
        PostDto postDto = postService.getById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long id){
        PostDto updatedPostDto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
