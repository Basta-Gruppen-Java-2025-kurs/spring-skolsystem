package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.model.Course;

public final class CourseMapper {

    public static CourseResponseDTO toResponseDto(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getMaxStudents()
        );
    }
}