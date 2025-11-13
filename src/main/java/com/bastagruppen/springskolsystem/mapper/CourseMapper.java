package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {
    public Course toEntity(CourseRequestDTO requestDTO) {
        return new Course(0L, requestDTO.getTitle(), requestDTO.getTeacher(), requestDTO.getMaxStudents());
    }

    public CourseResponseDTO toResponseDto(Course course) {
        return new CourseResponseDTO(course.getId(), course.getTitle(), course.getTeacher(), course.getMaxStudents());
    }
}
