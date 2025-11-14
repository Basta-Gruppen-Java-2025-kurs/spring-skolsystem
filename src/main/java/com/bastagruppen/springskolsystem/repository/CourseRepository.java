package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    @Query("""
            SELECT c
            FROM Course c
            LEFT JOIN FETCH c.enrollments
            WHERE c.title = :title
            """)
    Optional<Student> findByTitle(@Param("title") String title);
}