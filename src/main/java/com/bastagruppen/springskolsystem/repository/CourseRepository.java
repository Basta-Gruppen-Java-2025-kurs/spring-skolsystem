package com.bastagruppen.springskolsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.bastagruppen.springskolsystem.model.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CourseRepository extends InMemoryRepository<Course> {
    
    public Optional<Course> findCourseByTitle(final String title){
        return findAll().stream()
                .filter(course -> course.getTitle().equals(title))
                .findFirst();
    }

    public List<Course> findAllCoursesByTeacher(final String teacher){
        return findAll().stream()
                .filter(c -> c.getTeacher().equals(teacher))
                .collect(Collectors.toList());
    }
}
