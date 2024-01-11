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

    public List<PostDTO> getPosts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return postMapper.selectPosts(new RowBounds(offset, pageSize));
    }

    public List<PostDTO> getPostsByUserId(int userId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return postMapper.selectPostsByUserId(userId, new RowBounds(offset, pageSize));
    }

//    public PostDTO getPostByPostId(Long post_id) {
//        return postMapper.getPostByPostId(post_id);
//    }

    public List<CommentDTO> getCommentsByPostId(Long post_id) {
        return postMapper.getCommentsByPostId(post_id);
    }
}
