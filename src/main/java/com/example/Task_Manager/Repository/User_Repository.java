package com.example.Task_Manager.Repository;

import com.example.Task_Manager.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface User_Repository extends MongoRepository<User, String> {
    User findByusername (String username);
}
