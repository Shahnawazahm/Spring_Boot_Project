package com.myBlog.myBlog.service;

import com.myBlog.myBlog.payload.PostDTO;

import java.util.List;

public interface PostService {
    //implementation in postServiceImpl.java class
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(long id);

    List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    void deletePost(long id);
}