package com.myBlog.myBlog.service.impl;

import com.myBlog.myBlog.entity.Comment;
import com.myBlog.myBlog.entity.Post;
import com.myBlog.myBlog.exception.ResourceNotFoundException;
import com.myBlog.myBlog.payload.CommentDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import com.myBlog.myBlog.repository.CommentRepository;
import com.myBlog.myBlog.repository.PostRepository;
import com.myBlog.myBlog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

        // Configure ModelMapper for updating Comment
        modelMapper.addMappings(new PropertyMap<CommentDTO, Comment>() { //propertyMap(DTO,entity)
            @Override
            protected void configure() {
                // Skip id and email during mapping
                skip(destination.getId());
                skip(destination.getEmail());
            }
        });
    } //constructor based injection

    public CommentDTO createComment(CommentDTO commentDTO, long postId){
        Post post= postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException( "Post not found with id: "+postId)
        );
        Comment comment = new Comment();

        comment.setEmail(commentDTO.getEmail());
        comment.setText(commentDTO.getText());
        comment.setPost(post); //oneToMany

        Comment savedComment = commentRepository.save(comment);

        CommentDTO dto = new CommentDTO();
        dto.setId(savedComment.getId());
        dto.setEmail(savedComment.getEmail());
        dto.setText(savedComment.getText());

        return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO updateComment(long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Comment not found for Id: "+id)
        );

        //comment here is actual comment and commentDTO comment to be updated
        //so, converting commentDTO text to entity text via modelMapper
//        comment.setText(commentDTO.getText());

        // Use ModelMapper to update only the allowed fields (provided in constructor on this class)
        modelMapper.map(commentDTO, comment);


        Comment updatedComment = commentRepository.save(comment); //saving entity to db

        //putting entity data to dto again and returning back to controller
        return modelMapper.map(updatedComment,CommentDTO.class);
    }

}