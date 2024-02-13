package com.myBlog.myBlog.service;

import com.myBlog.myBlog.payload.CommentDTO;


public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, long postId);

    void deleteComment(long id);

    CommentDTO updateComment(long id, CommentDTO commentDTO);
}