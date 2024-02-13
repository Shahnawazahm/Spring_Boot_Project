package com.myBlog.myBlog.controller;

import com.myBlog.myBlog.payload.CommentDTO;
import com.myBlog.myBlog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/comments?postId=1 //postId as queryParameter and comment as json Object
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @RequestParam long postId //var name should match with the url type
    ){
        CommentDTO dto = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment deleted!",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable long id, @RequestBody CommentDTO commentDTO ){
        //commentDTO: data to be updated, id: for what id data to be updated
     CommentDTO dto = commentService.updateComment(id,commentDTO);
     return new ResponseEntity<>("Comment updated!",HttpStatus.OK);
    }
}