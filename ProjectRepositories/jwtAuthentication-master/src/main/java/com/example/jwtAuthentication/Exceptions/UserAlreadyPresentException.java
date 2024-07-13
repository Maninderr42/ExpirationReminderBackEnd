package com.example.jwtAuthentication.Exceptions;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
