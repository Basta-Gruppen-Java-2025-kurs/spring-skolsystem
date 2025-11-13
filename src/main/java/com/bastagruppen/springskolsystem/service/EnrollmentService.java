package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.mapper.EnrollmentMapper;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;
    private final StudentService studentService;
    private final CourseService courseService;

    public List<EnrollmentResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    public EnrollmentResponseDTO enroll(Long courseId, UUID studentId) {
        //TODO: get actual Course from CourseService
        return mapper.toResponseDTO(repository.save(new Enrollment(0L, studentService.findStudentById(studentId), null, new Date(System.currentTimeMillis()))));
    }
}
