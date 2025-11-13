package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.exception.DuplicateEnrollmentException;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import com.bastagruppen.springskolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Enrollment> getAll() {return repository.findAll();
    }

    public Enrollment enrollStudent(UUID studentId, Long courseId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        repository.findByStudentAndCourse(student,course)
                .ifPresent(e -> {throw new DuplicateEnrollmentException();
                });

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setDate(Date.valueOf(LocalDate.now()));

        return repository.save(enrollment);
    }
}
