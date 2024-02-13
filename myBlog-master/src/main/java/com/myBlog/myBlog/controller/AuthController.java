package com.myBlog.myBlog.controller;

import com.myBlog.myBlog.entity.Role;
import com.myBlog.myBlog.entity.User;
import com.myBlog.myBlog.payload.SignUpDTO;
import com.myBlog.myBlog.repository.RoleRepository;
import com.myBlog.myBlog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository= roleRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO){
        if(userRepository.existsByUsername(signUpDTO.getUsername()))
            return new ResponseEntity<>("Please pick different username", HttpStatus.BAD_REQUEST);

        if(userRepository.existsByEmail(signUpDTO.getEmail()))
            return new ResponseEntity<>("Please pick different emailID", HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setName(signUpDTO.getName());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        if(roleRepository.findByName(signUpDTO.getRoleType()).isPresent()){
            Role roles = roleRepository.findByName(signUpDTO.getRoleType()).get();
            //before saving we need to add role too, but roles in role entity has the set return type, so we need to convert to set first
           //approach 1:
//                Set<Role> convertRoleToSet= new HashSet<>();
//                convertRoleToSet.add(roles);
//                user.setRoles(convertRoleToSet);

            //approach 2:
                user.setRoles(Collections.singleton(roles));
        }

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!",HttpStatus.OK);
    }
}