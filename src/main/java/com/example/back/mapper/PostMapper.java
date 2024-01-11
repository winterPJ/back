package com.example.back.mapper;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> selectPosts();
    List<PostDTO> selectPostsByUserId(@Param("userId") int userId);

    PostDTO getPostByPostId(int post_id);
    List<CommentDTO> getCommentsByPostId(int post_id);

    int insertPost(PostDTO postDTO);

    int countById(int userId);

    int updatePost(PostDTO postDTO);

    int deletePost(int post_id);

}
