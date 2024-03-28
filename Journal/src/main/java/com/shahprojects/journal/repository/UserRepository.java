package com.shahprojects.journal.repository;

import com.shahprojects.journal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Object> {
    User findByUsername(String username);
}