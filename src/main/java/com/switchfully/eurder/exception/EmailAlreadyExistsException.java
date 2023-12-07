package com.switchfully.eurder.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("This email already exists.");
    }
}
