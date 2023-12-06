package com.switchfully.eurder.exception;

public class InvalidUserRegistrationException extends RuntimeException{
    public InvalidUserRegistrationException() {
        super("User creation input is invalid exception");
    }
}
