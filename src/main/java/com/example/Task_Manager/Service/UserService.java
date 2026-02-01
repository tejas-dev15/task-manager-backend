package com.example.Task_Manager.Service;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Repository.User_Repository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private User_Repository user_repository;

    @Autowired
    private Task_Repository task_repository;

    private static final PasswordEncoder password_encoder = new BCryptPasswordEncoder();
    public void CreateUser(User user){
        user.setPassword(password_encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user_repository.save(user);
    }

    public List<User> getAll(){
        return user_repository.findAll();
    }

    public void SaveUser(User user){
        user_repository.save(user);
    }

    public User findByusername(String username){
        return user_repository.findByusername(username);
    }

    public void DeleteUser(User user){
        List<Task> user_tasks = user.getTask();

        user_repository.delete(user);
    }

    public void delete_task(ObjectId id){
        task_repository.deleteById(String.valueOf(id));
    }

    public void CreateAdmin(User user){
        user.setPassword(password_encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN"));
        user_repository.save(user);
    }
}
