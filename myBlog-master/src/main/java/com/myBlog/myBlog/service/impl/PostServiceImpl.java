package com.myBlog.myBlog.service.impl;

import com.myBlog.myBlog.entity.Post;
import com.myBlog.myBlog.exception.ResourceNotFoundException;
import com.myBlog.myBlog.payload.PostDTO;
import com.myBlog.myBlog.repository.PostRepository;
import com.myBlog.myBlog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;
    /*third party library, spring ioc doesn't have any information which object to create and inject in this reference variable
    Thus we will get bean exception. To normalize the error we need to mention this in the class which is annotated with @SpringBootApplication
     */


    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    } //instead of using @Autowired we can use constructor based dependency injection for dependency injection

    //create post
    @Override //implemented method from PostSerImp
    public PostDTO createPost(PostDTO postDTO){

        //this method saves data to database but database work needs to be done within repository, thus using postRepository to save data

        //can't push DTO to DB, thus creating post obj
       /*
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        //entity object initialized with data
        OR
        */
        //using modelMapper() to covert objects from dto to entity
        Post post = new Post();
        modelMapper.map(postDTO,post); //map(dto obj, entity obj)
        //remember if you dont want to put every single entity then use configuration in constructor

       // Post post = mapToEntity(postDTO);

        //using repo to save the post onto DB
        Post savedPost = postRepository.save(post);

        /*
            //can't return savedPost, need to return DTO
            PostDTO dto = new PostDTO();

            //get data from db
            dto.setTitle(savedPost.getTitle());
            dto.setDescription(savedPost.getDescription());
            dto.setContent(savedPost.getContent());
        OR
        */

        //to covert entity back to dto
        PostDTO dto = modelMapper.map(savedPost, PostDTO.class); // map(entity, dto)
        return dto; //return DTO back to controller
    }

    //get post by id
    @Override
    public PostDTO getPostById(long id) { //gets post by id on the basis of id given
        Post post = postRepository.findById(id).orElseThrow( //supplier (functional interface), doesn't take input just produces output //shortcut to throw exception
                ()->new ResourceNotFoundException("Post not found with id: "+id) //
        ); //if post found, return post, else display the message provided

        //converting post obj to dto, thus creating obj of DTO
        PostDTO dto = new PostDTO();

        //get data from db
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto; //returning back to controller
    }

    //get all the posts
    @Override
    public List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); // for pageNo, page size and pageSort
        Page<Post> pagePost=postRepository.findAll(pageable);
        List<Post> posts= pagePost.getContent();

        List<PostDTO> dtos= posts.stream().map
                (post->modelMapper.map(post,PostDTO.class)) //using modelMapper() to convert entity to dto
                .collect(Collectors.toList());

        return dtos;

    }

    //delete a post
    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

}
/*
    SERVICE LAYER TAKES THE DTO, COPIES INTO ENTITY AND SAVES. AFTER SAVING PUTS SAVED CONTENT INTO DTO AND RETURNS BACK TO CONTROLLER
 */