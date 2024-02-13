package com.myBlog.myBlog.repository;

import com.myBlog.myBlog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name); //will return admin or user
}
