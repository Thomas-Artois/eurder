package com.switchfully.eurder.exception;

public class NotACustomerException extends RuntimeException{
    public NotACustomerException() {
        super("This is not a customer");
    }
}
