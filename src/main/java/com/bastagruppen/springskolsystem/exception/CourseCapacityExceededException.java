package com.bastagruppen.springskolsystem.exception;

import com.bastagruppen.springskolsystem.model.Course;

import static java.lang.String.format;

public class CourseCapacityExceededException extends RuntimeException {

    public CourseCapacityExceededException(Course course) {
        super(format("Course %s is already at maximum capacity: %d",
                course.getTitle(),
                course.getMaxStudents())
        );
    }
}