package com.stackroute.trackservice.exception;

public class EmptyTrackListException extends Exception
{
    private String message;

    public EmptyTrackListException() {
    }

    public EmptyTrackListException(String message)
    {
        super(message);
        this.message = message;
    }
}
