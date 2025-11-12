package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.service.StudentService;
import com.bastagruppen.springskolsystem.util.ICreate;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

import static com.bastagruppen.springskolsystem.mapper.StudentMapper.toDTO;
import static org.springframework.http.ResponseEntity.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> getAllStudents() {
        final Set<StudentDTO> allStudents = studentService.getAllStudents();
        return ok(allStudents);
    }

    @GetMapping(params = "email")
    public ResponseEntity<StudentDTO> getStudentByEmail(@RequestParam @Email String email) {
        final Student student = studentService.findStudentByEmail(email);
        return ok(toDTO(student));
    }

    @GetMapping("/search")
    public ResponseEntity<Set<StudentDTO>> getStudentsByName(@RequestParam String name) {
        Set<StudentDTO> students = studentService.findStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/filter")
    public ResponseEntity<Set<StudentDTO>> getStudentsByAgeRange(@RequestParam int minAge,
                                                                 @RequestParam int maxAge) {
        Set<StudentDTO> students = studentService.findStudentsByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Validated(ICreate.class) @RequestBody StudentDTO dto) {
        final Student student = studentService.registerStudent(dto);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return created(location).body(toDTO(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return noContent().build();
    }
}