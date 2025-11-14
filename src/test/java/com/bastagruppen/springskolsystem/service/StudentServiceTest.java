package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.exception.StudentNotFoundException;
import com.bastagruppen.springskolsystem.mapper.StudentMapper;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.util.Collections.emptySet;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void whenValidId_thenStudentShouldBeFound() {
        // given
        final UUID id = randomUUID();
        final String name = "John Doe";
        final int age = 18;
        final String email = "john.doe@su.se";

        final Student mockStudent = new Student(id, name, age, email, emptySet());
        when(studentRepository.findById(id)).thenReturn(of(mockStudent));

        // when
        final Student found = studentService.findStudentById(id);

        // then
        assertEquals(id, found.getId());
        assertEquals(name, found.getName());
        assertEquals(age, found.getAge());
        assertEquals(email, found.getEmail());
        assertEquals(mockStudent, found);
    }

    @Test
    void whenInvalidId_thenThrowException() {
        // given
        final UUID id = randomUUID();

        when(studentRepository.findById(id)).thenReturn(empty());

        // then
        assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById(id));
    }

    @Test
    void whenValidEmail_thenStudentShouldBeFound() {
        // given
        final String email = "john.doe@test.com";

        final Student mockStudent = new Student(randomUUID(), "John Doe", 18, email, emptySet());
        when(studentRepository.findByEmail(email)).thenReturn(of(mockStudent));

        // when
        final Student found = studentService.findStudentByEmail(email);

        // then
        assertEquals(mockStudent, found);
    }

    @Test
    void whenInvalidEmail_thenThrowException() {
        // given
        final String email = "john.doe@test.se";
        when(studentRepository.findByEmail(email)).thenReturn(empty());

        // then
        assertThrows(StudentNotFoundException.class, () -> studentService.findStudentByEmail(email));
    }

    @Test
    void whenFindAll_thenSetOfStudentDTOsShouldBeFound() {
        // given
        final List<Student> mockList = List.of(
                new Student(randomUUID(), "John Doe", 18, "john.doe@test.com", emptySet())
        );
        when(studentRepository.findAll()).thenReturn(mockList);

        // when
        final Set<StudentDTO> found = studentService.findAllStudents();

        // then
        assertEquals(1, found.size());
        assertEquals(mockList.getFirst().getId(), found.iterator().next().id());
    }
}