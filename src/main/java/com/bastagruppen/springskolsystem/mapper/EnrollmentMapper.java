package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.service.CourseService;
import com.bastagruppen.springskolsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentResponseDTO toResponseDTO(Enrollment enrollment) {
        return new EnrollmentResponseDTO(enrollment.getId(), enrollment.getStudent().getName(), enrollment.getCourse().getTitle(), enrollment.getDate().toLocalDate());
    }

    public Enrollment toEntity(EnrollmentRequestDTO requestDTO) {
        // TODO: get valid course from CourseService
        return new Enrollment(0L, studentService.findStudentById(requestDTO.getStudentId()), null, new Date(System.currentTimeMillis()));
    }
}
