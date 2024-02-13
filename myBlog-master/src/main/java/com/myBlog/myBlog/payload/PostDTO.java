package com.myBlog.myBlog.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

//same data as that of entity
public class PostDTO {
    private long id;
    private String title, description, content;
}