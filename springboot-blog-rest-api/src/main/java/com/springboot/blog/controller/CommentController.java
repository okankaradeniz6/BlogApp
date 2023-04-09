package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long id,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto createdCommentDto = commentService.createComment(id, commentDto);

        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @GetMapping("post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable(value = "postId") long postId){
        List<CommentDto> commentDtos = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId,
                                                     @PathVariable long commentId){
        CommentDto commentDto = commentService.getCommentsById(postId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
                                                    @PathVariable long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedCommentDto = commentService.updateComment(postId, commentId, commentDto);

        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId,
                                                @PathVariable long commentId){
        commentService.deleteComment(postId, commentId);

        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
