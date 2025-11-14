package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponseDTO toResponseDto(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getMaxStudents());
    }

    public Course toEntity(CourseRequestDTO courseDto){
        if(courseDto == null){return null;}

        return Course.create(
            courseDto.title(),
            courseDto.teacher(),
            courseDto.maxStudents()
        );
    }
}
