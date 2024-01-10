package com.example.back.controller;

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
}
