package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);



}
