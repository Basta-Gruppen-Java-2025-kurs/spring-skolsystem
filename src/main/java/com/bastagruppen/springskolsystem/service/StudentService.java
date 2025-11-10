package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.exception.StudentNotFoundException;
import com.bastagruppen.springskolsystem.mapper.StudentMapper;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static com.bastagruppen.springskolsystem.model.Student.register;
import static java.util.stream.Collectors.toUnmodifiableSet;

@Log4j2
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(final UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    public Student getStudentByEmail(final String email) {
        return studentRepository.findStudentByEmail(email).orElseThrow(StudentNotFoundException::new);
    }

    public Set<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public Student registerStudent(final StudentDTO studentDTO) {
        final Student student = register(
                studentDTO.name(),
                studentDTO.age(),
                studentDTO.email());

        return studentRepository.save(student);
    }
}