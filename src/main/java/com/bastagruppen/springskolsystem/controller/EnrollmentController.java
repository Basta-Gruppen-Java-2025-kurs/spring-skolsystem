package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/enrollments")
@Validated
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService service;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAll() {
        return ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@Validated @RequestBody EnrollmentRequestDTO requestDTO) {
        return ok(service.enroll(requestDTO));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Set<StudentDTO>> listCourseStudents(@PathVariable UUID courseId) {
        return ok(service.listStudentsByCourseId(courseId));
    }
}