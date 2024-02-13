package com.myBlog.myBlog.exception;

import com.myBlog.myBlog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

//exception handling class
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { //R..E..E..C.. class has all the methods used to handle exceptions
    @ExceptionHandler(ResourceNotFoundException.class) //ResourceNotFoundException
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails= new ErrorDetails(e.getMessage(), webRequest.getDescription(true), new Date());
        //when exception occurs, provide exception msg; exception description, and the date when exception occurred
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}