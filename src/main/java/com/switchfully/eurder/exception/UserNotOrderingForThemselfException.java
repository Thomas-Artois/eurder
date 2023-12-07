package com.switchfully.eurder.exception;

public class UserNotOrderingForThemselfException extends RuntimeException{
    public UserNotOrderingForThemselfException() {
        super("This user is not ordering for themself.");
    }
}
