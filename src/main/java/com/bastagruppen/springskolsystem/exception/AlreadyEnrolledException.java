package com.bastagruppen.springskolsystem.exception;

import static java.lang.String.format;

public class AlreadyEnrolledException extends RuntimeException {

    public AlreadyEnrolledException(String courseTitle, String studentName) {
        super(format("Student %s is already enrolled in %s", courseTitle, studentName));
    }
}