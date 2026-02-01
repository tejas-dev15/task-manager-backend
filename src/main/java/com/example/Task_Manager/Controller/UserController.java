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
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;



    @DeleteMapping("/Delete-user")
    public ResponseEntity<?> DeleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByusername(username);
        userService.DeleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/change-user-specs")
    public ResponseEntity<?> ChangeUserSpecs(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User UserINDB = userService.findByusername(username);
        if(UserINDB!=null){
            UserINDB.setUsername(user.getUsername());
            UserINDB.setPassword(user.getPassword());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-my-tasks")
    public ResponseEntity<?> GetUserSpecificTask(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Task> spec_task =  taskService.Specific_task(username);
        if(!spec_task.isEmpty()){
            return new ResponseEntity<>(spec_task, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Delete-my-tasks/{id}")
    public ResponseEntity<?> DeleteMyTasks(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByusername(username);
        boolean removed = false;
        removed =user.getTask().removeIf(x -> x.getId().equals(id));
        if(removed) {
            taskService.DeleteById(id);
            taskService.saveuser(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
