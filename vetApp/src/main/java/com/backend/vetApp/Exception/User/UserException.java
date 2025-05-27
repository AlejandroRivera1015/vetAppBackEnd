package com.backend.vetApp.Exception.User;

public class UserException extends  Exception {
    private Long userId;
    private String userEmail;


    public UserException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public UserException(String message, String email) {
        super(message);
        this.userEmail = email;

    }
}