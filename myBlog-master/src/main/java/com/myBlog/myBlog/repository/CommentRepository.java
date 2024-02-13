package com.myBlog.myBlog.repository;

import com.myBlog.myBlog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}