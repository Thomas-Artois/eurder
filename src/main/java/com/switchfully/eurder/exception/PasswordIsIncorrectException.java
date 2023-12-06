package com.switchfully.eurder.exception;

public class PasswordIsIncorrectException extends RuntimeException{
    public PasswordIsIncorrectException() {
        super("Password is incorrect");
    }
}
