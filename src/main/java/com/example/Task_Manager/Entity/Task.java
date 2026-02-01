package com.example.Task_Manager.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Task")
@Data
@NoArgsConstructor
public class Task {

    private ObjectId id;
    private String title;
    private String description;
    private LocalDateTime date;
}
