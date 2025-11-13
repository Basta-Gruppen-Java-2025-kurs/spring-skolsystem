package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.exception.CourseNotFoundException;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import com.bastagruppen.springskolsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final StudentService studentService;
    private final CourseRepository courseRepository;

    public EnrollmentResponseDTO toResponseDTO(Enrollment enrollment) {
        return new EnrollmentResponseDTO(enrollment.getId(), enrollment.getStudent().getName(), enrollment.getCourse().getTitle(), enrollment.getDate().toLocalDate());
    }

    public Enrollment toEntity(EnrollmentRequestDTO requestDTO) {
        return new Enrollment(0L, studentService.findStudentById(requestDTO.getStudentId()), courseRepository.findById(requestDTO.getCourseId()).orElseThrow(CourseNotFoundException::new), new Date(System.currentTimeMillis()));
    }
}
