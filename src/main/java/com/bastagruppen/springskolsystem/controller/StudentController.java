package com.bastagruppen.springskolsystem.controller;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.service.StudentService;
import com.bastagruppen.springskolsystem.util.ICreate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

import static com.bastagruppen.springskolsystem.mapper.StudentMapper.toDTO;
import static org.springframework.http.ResponseEntity.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping(params = "email")
    public ResponseEntity<StudentDTO> findStudentByEmail(@RequestParam @Email String email) {
        final Student student = studentService.findStudentByEmail(email);
        return ok(toDTO(student));
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> findStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer ageAfter,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {

        if (name != null)
            return ok(studentService.findStudentsByNameSorted(name));

        if (ageAfter != null)
            return ok(studentService.findByAgeAfterSorted(ageAfter));

        if (minAge != null && maxAge != null)
            return ok(studentService.findStudentsByAgeRange(minAge, maxAge));

        return ok(studentService.findAllStudents());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getStudentCount() {
        long count = studentService.getNumberOfRegisteredStudents();
        return ok(count);
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

    @PatchMapping("/{id}")
    public Student patchManager(@PathVariable UUID id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        return studentService.patch(id, jsonPatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return noContent().build();
    }
}