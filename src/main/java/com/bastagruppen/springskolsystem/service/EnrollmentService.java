package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.mapper.EnrollmentMapper;
import com.bastagruppen.springskolsystem.mapper.StudentMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.bastagruppen.springskolsystem.mapper.EnrollmentMapper.toResponseDTO;
import static com.bastagruppen.springskolsystem.model.Enrollment.newEnrollment;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toUnmodifiableSet;

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
        final LocalDate date = ((requestDTO.date() != null) ? requestDTO.date() : now(clock));

        return toResponseDTO(repository.save(newEnrollment(student, course, requestDTO.grade(), date)));
    }

    @Transactional(readOnly = true)
    public Set<StudentDTO> listStudentsByCourseId(UUID courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(Enrollment::getStudent)
                .map(StudentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }
}
