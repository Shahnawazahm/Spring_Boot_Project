package com.myBlog.myBlog.repository;

import com.myBlog.myBlog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
   /*
    Should extend JpaRepo for getting database related functionalities.
    We can extend CRUDRepository too, but JPARepo has more functionalities
    than CRUDRepo like Pagination and sorting. Also in CRUDRepo findAll() returns
    iterable but in JPARepo findAll() returns a list.
   */
}