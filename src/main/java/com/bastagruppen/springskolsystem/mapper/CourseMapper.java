package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.model.Course;

public final class CourseMapper {
    
    public static CourseDTO toDTO(Course course){
        if(course == null){return null;}
        return CourseDTO.builder()
        .title(course.getTitle())
        .teacher(course.getTeacher())
        .maxStudents(course.getMaxStudents())
        .enrolledStudents(course.getEnrolledStudents())
        .build();
    }

    public static Course fromDTO(CourseDTO dtoCourse){
        if(dtoCourse == null){return null;}

        return Course.create(
            dtoCourse.title(),
            dtoCourse.teacher(),
            dtoCourse.maxStudents(),
            dtoCourse.enrolledStudents()
        );
    }
}
