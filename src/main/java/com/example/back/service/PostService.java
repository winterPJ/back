package com.example.back.service;

import com.example.back.dto.CommentDTO;
import com.example.back.dto.PostDTO;
import com.example.back.mapper.PostMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public List<PostDTO> getPosts() {
        return postMapper.selectPosts();
    }

    public List<PostDTO> getPostsByUserId(int userId) {
        return postMapper.selectPostsByUserId(userId);
    }

    public PostDTO getPostByPostId(int post_id) {
        return postMapper.getPostByPostId(post_id);
    }

    public List<CommentDTO> getCommentsByPostId(int post_id) {
        return postMapper.getCommentsByPostId(post_id);
    }


    @Transactional
    public boolean createPost(PostDTO postDTO) {
        if (postMapper.countById(postDTO.getUser_id()) == 0) {
            return false;
        }

        return postMapper.insertPost(postDTO) > 0;
    }

}
