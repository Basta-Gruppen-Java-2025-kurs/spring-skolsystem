package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repository;

    public List<Enrollment> getAll() {
        return repository.findAll();
    }
}
