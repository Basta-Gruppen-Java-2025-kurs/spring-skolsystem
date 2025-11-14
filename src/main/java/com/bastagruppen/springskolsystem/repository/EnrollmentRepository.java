package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    long countEnrollmentByCourseId(UUID courseId);
}