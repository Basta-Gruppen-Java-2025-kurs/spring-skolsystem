package com.bastagruppen.springskolsystem.exception;

public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String message) {
        super(message);
    }
}