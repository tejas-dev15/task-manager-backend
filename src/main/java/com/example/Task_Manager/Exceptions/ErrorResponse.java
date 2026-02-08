package com.example.Task_Manager.Exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String Message;
    private String path;

    public ErrorResponse(LocalDateTime timestamp, int status, String Message, String path ){

        this.timestamp = timestamp;
        this.status = status;
        this.Message = Message;
        this.path = path;
    }
}
