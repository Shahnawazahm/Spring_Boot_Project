package com.myBlog.myBlog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetails {
    private String message, uri;
    private Date date;
}