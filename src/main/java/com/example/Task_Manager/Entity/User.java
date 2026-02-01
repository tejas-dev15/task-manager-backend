package com.example.Task_Manager.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "User")
@Data
@NoArgsConstructor
public class User {
   private String ObjectId;
   @Id
   @NonNull
   private String username;
   @NonNull
   private String password;
   @DBRef
   private List<Task> task = new ArrayList<>();
   private List<String> roles;
}
