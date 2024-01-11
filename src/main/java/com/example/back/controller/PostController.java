package com.example.back.controller;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import com.example.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/get")
    public List<PostDTO> getPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return postService.getPosts(page, pageSize);
    }

    @GetMapping("/get/{userId}")
    public List<PostDTO> getPostsByUserId(@PathVariable int userId,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        return postService.getPostsByUserId(userId, page, pageSize);
    }

    @GetMapping("/get/{post_id}")
    public PostDTO getPostByPostId(@PathVariable Long post_id) {
        return postService.getPostByPostId(post_id);
    }

    @GetMapping("/{post_id}/comment")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long post_id) {
        return postService.getCommentsByPostId(post_id);
    }
}
