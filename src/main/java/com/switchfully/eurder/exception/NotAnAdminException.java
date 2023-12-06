package com.switchfully.eurder.exception;

public class NotAnAdminException extends RuntimeException{
    public NotAnAdminException() {
        super("Not an admin");
    }
}
