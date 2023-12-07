package com.switchfully.eurder.exception;

public class OrderDoesntExistException extends RuntimeException{
    public OrderDoesntExistException() {
        super("This order doesn't exist.");
    }
}
