package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.mapper.EnrollmentMapper;
import com.bastagruppen.springskolsystem.mapper.StudentMapper;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;
    private final StudentMapper studentMapper;

    public List<EnrollmentResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO requestDTO) {
        return mapper.toResponseDTO(repository.save(mapper.toEntity(requestDTO)));
    }

    public List<StudentDTO> listCourseStuents(Long courseId) {
        return repository.findStudentsByCourseId(courseId).stream().map(studentMapper::toDTO).toList();
    }
}
