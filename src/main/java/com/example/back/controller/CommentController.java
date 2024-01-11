package com.example.back.controller;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import com.example.back.dto.UserDTO;
import com.example.back.service.CommentService;
import com.example.back.service.PostService;
import com.example.back.utils.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CommentDTO commentDTO, @SessionAttribute(name = "user", required = false) UserDTO user) {
        commentDTO.setUser_id(user.getId());
        boolean success = commentService.createComment(commentDTO);
        ApiResponse response = new ApiResponse(success, success ? "댓글 작성 완료" : "댓글 작성 실패");

        return ResponseEntity.ok(response);
    }

}
