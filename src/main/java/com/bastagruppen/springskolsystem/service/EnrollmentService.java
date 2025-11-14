package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.exception.AlreadyEnrolledException;
import com.bastagruppen.springskolsystem.exception.CourseCapacityExceededException;
import com.bastagruppen.springskolsystem.mapper.EnrollmentMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;

import static com.bastagruppen.springskolsystem.mapper.EnrollmentMapper.toResponseDTO;
import static com.bastagruppen.springskolsystem.model.Enrollment.newEnrollment;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final Clock clock;
    private final EnrollmentRepository repository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Transactional(readOnly = true)
    public List<EnrollmentResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(EnrollmentMapper::toResponseDTO)
                .toList();
    }

    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO requestDTO) {
        final Course course = courseService.findById(requestDTO.courseId());
        final Student student = studentService.findStudentById(requestDTO.studentId());

        try {
            checkCourseCapacity(course);

            final Enrollment enrollment = newEnrollment(
                    student,
                    course,
                    requestDTO.grade(),
                    requestDTO.date() != null ? requestDTO.date() : now(clock)
            );
            final Enrollment saved = repository.saveAndFlush(enrollment);

            return toResponseDTO(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyEnrolledException(course.getTitle(), student.getName());
        }
    }

    private void checkCourseCapacity(Course course) {
        final long enrolled = repository.countEnrollmentByCourseId(course.getId());
        final int limit = course.getMaxStudents();

        if (enrolled >= limit)
            throw new CourseCapacityExceededException(course);
    }
}
