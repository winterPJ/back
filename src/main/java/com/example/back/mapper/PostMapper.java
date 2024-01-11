package com.example.back.mapper;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> selectPosts(RowBounds rowBounds);
    List<PostDTO> selectPostsByUserId(@Param("userId") int userId, RowBounds rowBounds);

    PostDTO getPostByPostId(int post_id);
    List<CommentDTO> getCommentsByPostId(Long post_id);

    int insertPost(PostDTO postDTO);

    int countById(int userId);
}
