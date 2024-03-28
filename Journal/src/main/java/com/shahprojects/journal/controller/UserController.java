package com.shahprojects.journal.controller;

import com.shahprojects.journal.entity.User;
import com.shahprojects.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
   UserService userService;
//    public UserController(UserService userService){
//       this.userService = userService;
//   }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User newEntry) {
        userService.saveEntry(newEntry);
    }
    @PutMapping
    public ResponseEntity<?> updateEntry(@RequestBody User updateEntry) {
        User userInDB = userService.findByUsername(updateEntry.getUsername());

        if(userInDB!=null){
            userInDB.setUsername(updateEntry.getUsername());
            userInDB.setPassword(updateEntry.getPassword());
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
