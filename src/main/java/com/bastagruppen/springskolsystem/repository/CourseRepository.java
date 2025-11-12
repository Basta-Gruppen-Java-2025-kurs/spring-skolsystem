package com.bastagruppen.springskolsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bastagruppen.springskolsystem.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    public Optional<Course> findCourseByTitle(final String title);
    public List<Course> findAllCoursesByTeacher(final String teacher);
}
