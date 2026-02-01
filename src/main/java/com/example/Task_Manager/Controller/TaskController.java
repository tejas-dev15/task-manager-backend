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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/Healthcheck")
    public String healthcheck(){
        return "Helath ok";
    }



    @PostMapping("/create-task")
    public ResponseEntity<?> create_task(@RequestBody Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (task != null) {
            taskService.SaveUser(task, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteTask(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByusername(username);
        boolean removed = false;
         removed =user.getTask().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.SaveUser(user);
            taskService.DeleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/Modify-tasks/{id}")
    public ResponseEntity<?> ModifyTasks( @PathVariable ObjectId id, @RequestBody Task mytask){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByusername(username);
        List<Task> collect = user.getTask().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
                 Optional<Task> task = taskService.findById(id);
                 Task old = task.get();
                 old.setDate(LocalDateTime.now());
                 old.setTitle(mytask.getTitle() != null && !mytask.getTitle().equals("") ? mytask.getTitle() : old.getTitle());
                 old.setDescription(mytask.getDescription() != null && !mytask.getDescription().equals("") ? mytask.getDescription() : old.getDescription());
                 taskService.SaveTask(old);
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
