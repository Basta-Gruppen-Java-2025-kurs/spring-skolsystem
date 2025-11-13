package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@Validated
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService service;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> enrollStudent(@RequestParam UUID studentId,
                                           @RequestParam Long courseId){
        return ResponseEntity.ok(service.enrollStudent(studentId,courseId));
    }
}
