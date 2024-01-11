package com.example.back.mapper;

import com.example.back.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {


    boolean createComment(CommentDTO commentDTO);
}
