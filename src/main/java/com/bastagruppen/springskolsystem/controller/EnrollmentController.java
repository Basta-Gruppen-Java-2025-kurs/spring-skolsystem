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

@RestController
@RequestMapping("/api/enrollments")
@Validated
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService service;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@Validated @RequestBody EnrollmentRequestDTO requestDTO) {
        return ResponseEntity.ok(service.enroll(requestDTO));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentDTO>> listCourseStudents(@RequestParam Long courseId) {
        return ResponseEntity.ok(service.listCourseStuents(courseId));
    }
}
