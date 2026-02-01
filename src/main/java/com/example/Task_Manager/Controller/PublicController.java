package com.example.Task_Manager.Controller;

import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Service.TaskService;
import com.example.Task_Manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> Create_User(@RequestBody User user){
        if(user !=null){
            userService.CreateUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
