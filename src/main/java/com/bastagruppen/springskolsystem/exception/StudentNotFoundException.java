package com.bastagruppen.springskolsystem.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("Student not found");
    }
}
