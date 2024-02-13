package com.myBlog.myBlog.controller;

import com.myBlog.myBlog.payload.PostDTO;
import com.myBlog.myBlog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    } //constructor based injection

    //create/store post onto database
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        //@RB takes json and put in DTO, DTO is used to send back a custom response
        PostDTO dto = postService.createPost(postDTO);
        //DTO layer to service layer, as db work needs to be done in service or model layer
        //from post interface to post implementation then database work there, and pass data back here.
        return new ResponseEntity<>(dto, HttpStatus.CREATED); //HttpStatus.created for 201
    }

    /*
    ONCE WE USE POSTMAN/VIEW, CLICKING ON SEND IN POSTMAN, POSTMAN WILL TAKE THE DATA VIA THE URL IT WILL CALL
    THE CONTROLLER (@RestController with @PostMapping("url") will be called), THE CONTROLLER TAKES THE DATA TO DTO
    AND LATER CALLS THE SERVICE LAYER. AFTER GETTING BACK DTO DATA FROM SERVICE,
    THE CONTROLLER PUSH DATA ONTO POSTMAN/VIEW.

 */

    //https://localhost:8080/api/posts/particular?id=x //where x is any id you want to search
    @GetMapping("/particular")
    public ResponseEntity<PostDTO> getPostById(@RequestParam long id){
        PostDTO dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //get all the posts
    //localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc //sortDir: sort direction
    @GetMapping
    public List<PostDTO> getAllPosts(
            @RequestParam(name="pageNo",required = false, defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize", required = false, defaultValue = "3") int pageSize,
            /*here page size we set to 3 though in url if we didn't mention page size, 3 will be set as default.
             If a different value is set in url, then that will overwrite the default value
             */
            //now sorting basis on title
            @RequestParam(name="sortBy", required=false, defaultValue = "id") String sortBy,
            @RequestParam(name="sortDir", required = false, defaultValue = "id") String sortDir
    ){
        List<PostDTO> postDTOS = postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
        return postDTOS; //response here is always 200 by default that is why not returning ResponseEntity<>
    }

    //delete post
    //https://localhost:8080:api/posts/id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted!",HttpStatus.OK);
    }
}