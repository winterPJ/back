package com.example.back.mapper;

import com.example.back.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> selectPosts(RowBounds rowBounds);
    List<PostDTO> selectPostsByUserId(@Param("userId") int userId, RowBounds rowBounds);
}
