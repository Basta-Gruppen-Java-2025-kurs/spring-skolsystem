package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;

    public List<CourseResponseDTO> listCourses() {
        return repository.findAll().stream().map(mapper::toResponseDTO).toList();
    }
}
