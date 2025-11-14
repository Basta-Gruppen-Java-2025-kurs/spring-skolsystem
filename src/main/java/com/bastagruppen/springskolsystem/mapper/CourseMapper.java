package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.model.Course;

public final class CourseMapper {

    public static CourseResponseDTO toResponseDto(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getMaxStudents()

    public static CourseDTO toDTO(Course course){
        if(course == null){return null;}
        return CourseDTO.builder()
        .title(course.getTitle())
        .teacher(course.getTeacher())
        .maxStudents(course.getMaxStudents())
        .build();
    }

    public static Course fromDTO(CourseDTO courseDto){
        if(courseDto == null){return null;}

        return Course.create(
            courseDto.title(),
            courseDto.teacher(),
            courseDto.maxStudents()
        );
    }
}
