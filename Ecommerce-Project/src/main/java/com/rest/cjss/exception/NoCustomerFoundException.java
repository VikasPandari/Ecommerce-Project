package com.rest.cjss.exception;

public class NoCustomerFoundException extends RuntimeException{
    public NoCustomerFoundException(String message) {
        super(message);
    }
}
