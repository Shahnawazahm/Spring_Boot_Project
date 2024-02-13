package com.myBlog.myBlog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data //for automatic getter and setter
@Table(name="posts") // posts name in db
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String title, description, content;

    //one post can have multiple comments
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "post")
    //mapped by post which is returned by @ManyToOne in Comment class, CascadeType.All, if post is deleted, comments will be also deleted
    private List<Comment> comments;//many will be comments
}