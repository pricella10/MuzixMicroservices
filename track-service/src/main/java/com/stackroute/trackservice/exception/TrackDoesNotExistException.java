package com.stackroute.trackservice.exception;

public class TrackDoesNotExistException extends Exception
{
    private String message;

    public TrackDoesNotExistException() {
        super();
    }

    public TrackDoesNotExistException(String message) {
        super(message);
        this.message = message;
    }
}
