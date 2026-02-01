package com.example.Task_Manager.Repository;

import com.example.Task_Manager.Entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Task_Repository extends MongoRepository<Task, String> {
}
