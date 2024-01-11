package com.example.back.service;


import com.example.back.dto.CommentDTO;
import com.example.back.mapper.CommentMapper;
import com.example.back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    public boolean createComment(CommentDTO commentDTO) {
        return mapper.createComment(commentDTO);
    }



}
