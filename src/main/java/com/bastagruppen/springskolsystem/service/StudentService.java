package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.exception.StudentNotFoundException;
import com.bastagruppen.springskolsystem.mapper.StudentMapper;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.bastagruppen.springskolsystem.model.Student.register;
import static java.util.stream.Collectors.toUnmodifiableSet;

@Log4j2
@RequiredArgsConstructor
@Service
public class StudentService {

    private final ObjectMapper objectMapper;
    private final StudentRepository studentRepository;

    public Student findStudentById(final UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student findStudentByEmail(final String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(StudentNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Set<StudentDTO> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public List<StudentDTO> findStudentsByNameSorted(String name) {
        return studentRepository.findByNameContainingIgnoreCaseSortedByName(name)
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }

    public long getNumberOfRegisteredStudents() {
        return studentRepository.getNumberOfRegisteredStudents();
    }

    @Transactional(readOnly = true)
    public Set<StudentDTO> findStudentsByAgeRange(int minAge, int maxAge) {
        if (minAge < 0 || maxAge < 0)
            throw new IllegalArgumentException("Age cannot be negative");

        if (minAge > maxAge)
            throw new IllegalArgumentException("MinAge cannot be greater than MaxAge");

        return studentRepository.findByAgeBetween(minAge, maxAge)
                .stream()
                .map(StudentMapper::toDTO)
                .collect(toUnmodifiableSet());
    }

    public List<StudentDTO> findByAgeAfterSorted(int ageAfter) {
        if (ageAfter < 0)
            throw new IllegalArgumentException("Age cannot be negative");

        return studentRepository.findByAgeAfterSortedByAge(ageAfter)
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
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

    public Student patch(UUID id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        Student student = findStudentById(id);
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(student, JsonNode.class));

        return studentRepository.save(objectMapper.treeToValue(patched, Student.class));
    }
}