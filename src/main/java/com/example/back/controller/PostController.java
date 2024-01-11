package com.example.back.controller;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import com.example.back.dto.UserDTO;
import com.example.back.service.PostService;
import com.example.back.service.UserService;
import com.example.back.utils.Validation;
import com.example.back.utils.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/get/")
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/get/{userId}")
    public List<PostDTO> getPostsByUserId(@PathVariable int userId) {
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/{post_id}")
    public PostDTO getPostByPostId(@PathVariable int post_id) {
        return postService.getPostByPostId(post_id);
    }

    @GetMapping("/{post_id}/comment")
    public List<CommentDTO> getCommentsByPostId(@PathVariable int post_id) {
        return postService.getCommentsByPostId(post_id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPost(@RequestBody PostDTO postDTO, @SessionAttribute(name = "user", required = false) UserDTO user) {
        postDTO.setUser_id(user.getId());
        boolean success = postService.createPost(postDTO);
        ApiResponse response = new ApiResponse(success, success ? "글 작성 완료" : "실패: 존재하지 않는 user_id");

        return ResponseEntity.ok(response);
    }



}
