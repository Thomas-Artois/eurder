package com.switchfully.eurder.controller;

import com.switchfully.eurder.exception.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    private void emailAlreadyExistsException(EmailAlreadyExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private void userNotFoundException(UserNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NotAnAdminException.class)
    private void notAnAdminException(NotAnAdminException e, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(PasswordIsIncorrectException.class)
    private void passwordIsIncorrectException(PasswordIsIncorrectException e, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    private void itemNotFoundException(ItemNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NotACustomerException.class)
    private void notACustomerException(NotACustomerException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserNotOrderingForThemselfException.class)
    private void userNotOrderingForThemselfException(UserNotOrderingForThemselfException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(OrderDoesntExistException.class)
    private void orderDoesntExistException(OrderDoesntExistException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
}
