package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
