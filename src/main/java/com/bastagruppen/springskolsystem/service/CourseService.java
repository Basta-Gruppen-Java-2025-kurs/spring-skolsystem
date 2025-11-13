package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;

    public CourseResponseDTO findById(Long id) {
        return repository.findById(id).map(mapper::toResponseDto).orElse(null);
    }

}
