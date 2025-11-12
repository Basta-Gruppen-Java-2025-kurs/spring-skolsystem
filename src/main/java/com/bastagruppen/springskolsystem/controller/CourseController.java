package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Validated
public class CourseController {
    private final CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> listCourses() {
        return ResponseEntity.ok(service.listCourses());
    }
}
