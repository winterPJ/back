package com.example.back.service;


import com.example.back.dto.CommentDTO;
import com.example.back.mapper.CommentMapper;
import com.example.back.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentMapper mapper;

    public CommentService(CommentMapper mapper) {
        this.mapper = mapper;
    }




}
