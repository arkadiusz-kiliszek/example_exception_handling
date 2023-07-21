package com.example.example_exception_handling.exception;

public class CustomerAlreadyExistsException
        extends RuntimeException {

    private String message;

    public CustomerAlreadyExistsException() {}

    public CustomerAlreadyExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}