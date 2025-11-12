package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
