package com.example.Task_Manager.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String message){
        super(message);
    }
}

