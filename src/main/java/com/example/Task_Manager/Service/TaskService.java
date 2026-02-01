package com.example.Task_Manager.Service;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Repository.User_Repository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    @Autowired
    private Task_Repository task_repository;

    @Autowired
    private User_Repository user_repository;


    public void SaveUser(Task task , String username){
        User user =   user_repository.findByusername(username);
        task.setDate(LocalDateTime.now());
        Task saved = task_repository.save(task);
        user.getTask().add(saved);
        user_repository.save(user);
    }

    public void saveuser (String username){
        User user = user_repository.findByusername(username);
        user_repository.save(user);
    }

    public void SaveTask(Task task){
        task_repository.save(task);
    }
    public List<Task> Specific_task(String username){
        User user = user_repository.findByusername(username);
        return user.getTask();
    }
    public void DeleteById(ObjectId id){
        task_repository.deleteById(String.valueOf(id));
    }

    public Optional<Task> findById(ObjectId id){
       return  task_repository.findById(String.valueOf(id));
    }
}
