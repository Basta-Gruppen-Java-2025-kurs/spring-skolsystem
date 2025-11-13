package com.bastagruppen.springskolsystem.exception;

public class DuplicateEnrollmentException extends RuntimeException{
    public DuplicateEnrollmentException(String message) {
        super(message);
    }

    public DuplicateEnrollmentException() {
        super("Student is already enrolled in this course");
    }
}
