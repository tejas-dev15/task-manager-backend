package com.example.Task_Manager.Controller;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Service.TaskService;
import com.example.Task_Manager.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/get-all-user")
    public ResponseEntity<?> GetAllTasks(){
         List<User> all = userService.getAll();
         if(all !=null){
             return new ResponseEntity<>(all, HttpStatus.OK);
         }else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

    @DeleteMapping("/Delete-user/{username}")
    public ResponseEntity<?> DeleteUser(@PathVariable String username){
        User user = userService.findByusername(username);
        userService.DeleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/Delete-task/{id}")
    public ResponseEntity<?> DeleteTask(@PathVariable ObjectId id){
        taskService.DeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createadmin(@RequestBody User user){
        userService.CreateAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
