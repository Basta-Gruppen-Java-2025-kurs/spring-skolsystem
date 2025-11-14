package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    long countEnrollmentByCourseId(UUID courseId);

    @Query( "SELECT s " +
            "FROM Student s " +
            "LEFT JOIN FETCH s.enrollments e " +
            "LEFT JOIN FETCH e.course c " +
            "WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") UUID courseId);
}