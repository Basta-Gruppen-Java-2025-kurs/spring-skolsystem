package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final StudentRepository repository;
}
