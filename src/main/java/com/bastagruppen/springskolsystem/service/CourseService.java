package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final StudentRepository repository;

    public CourseService(StudentRepository repository) {
        this.repository = repository;
    }
}
