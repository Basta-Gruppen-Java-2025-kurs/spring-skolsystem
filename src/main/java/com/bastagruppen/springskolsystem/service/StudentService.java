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
    private final StudentMapper studentMapper;

    public Student findStudentById(final UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    public Student findStudentByEmail(final String email) {
        return studentRepository.findByEmail(email).orElseThrow(StudentNotFoundException::new);
    }

    public Set<StudentDTO> findStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(studentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public Set<StudentDTO> findStudentsByAgeRange(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge)
                .stream()
                .map(studentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public Set<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public Student registerStudent(final StudentDTO studentDTO) {
        final Student student = register(
                studentDTO.name(),
                studentDTO.age(),
                studentDTO.email());

        return studentRepository.save(student);
    }

    public void deleteStudent(final UUID id) {
        final Student student = findStudentById(id);
        studentRepository.delete(student);
    }
}