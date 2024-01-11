package com.example.back.controller;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import com.example.back.dto.UserDTO;
import com.example.back.service.PostService;
import com.example.back.service.UserService;
import com.example.back.utils.Validation;
import com.example.back.utils.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

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

    @GetMapping("/{post_id}")
    public PostDTO getPostByPostId(@PathVariable int post_id) {
        return postService.getPostByPostId(post_id);
    }

    @GetMapping("/{post_id}/comment")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long post_id) {
        return postService.getCommentsByPostId(post_id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPost(@RequestBody PostDTO postDTO) {
        boolean success = postService.createPost(postDTO);
        ApiResponse response = new ApiResponse(success, success ? "글 작성 완료" : "실패: 존재하지 않는 user_id");

        return ResponseEntity.ok(response);
    }

}
