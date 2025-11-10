package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class StudentRepository extends InMemoryRepository<Student> {

    public Optional<Student> findStudentByEmail(final String email) {
        return findAll().stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }
}