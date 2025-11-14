package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.exception.CourseNotFoundException;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.bastagruppen.springskolsystem.mapper.CourseMapper.toResponseDto;
import static com.bastagruppen.springskolsystem.model.Course.create;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public Course findById(UUID id) {
        return repository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public CourseResponseDTO createCourse(final CourseRequestDTO request) {
        final Course course = create(
                request.title(),
                request.teacher(),
                request.maxStudents());

        final Course saved = repository.save(course);
        return toResponseDto(saved);
    }
}