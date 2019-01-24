package com.stackroute.userservice.exception;

public class UserDoesNotExistException extends Exception
{
    private String message;

    public UserDoesNotExistException() {
        super();
    }

    public UserDoesNotExistException(String message) {
        super(message);
        this.message = message;
    }
}
