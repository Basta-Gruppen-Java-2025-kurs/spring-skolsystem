package com.bastagruppen.springskolsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.service.CourseService;
import com.bastagruppen.springskolsystem.util.ICreate;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    //1️⃣ GET - Fetch all courses
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        final List<CourseResponseDTO> courses = service.getAllCourses();
        return ok(courses);
    }

    //2️⃣ GET - Fetch course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable UUID id){
        final CourseResponseDTO course = service.getCourseById(id);
        return ok(course);
    }

    //3️⃣ GET - Fetch course by Title
    @GetMapping(params = "title")
    public ResponseEntity<CourseResponseDTO> getCourseByTitle(@RequestParam String title){
        final CourseResponseDTO course = service.getCourseByTitle(title);
        return ResponseEntity.ok(course);
    }

    //4️⃣ POST - Create course
    @PostMapping
    @Validated(ICreate.class)
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseDTO){
        final CourseResponseDTO course = service.createCourse(courseDTO);
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(course.id())
            .toUri();

            return created(uriLocation).body(course);
    }

    //5️⃣ DEL - Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id){
        boolean success = service.deleteCourse(id);
        return ok(success ? "Course deleted" : "Failed to delete course");
    }

    //6️⃣ PUT - Update Teacher
    @PutMapping("/{id}/updateTeacher")
    public ResponseEntity<CourseResponseDTO> updateTeacher(@PathVariable UUID id, @RequestBody String teacher){
        final CourseResponseDTO course = service.updateTeacher(id, teacher);
        return ok(course);
    }

    //7️⃣ PUT - Update Max Students
    @PutMapping("/{id}/updateMaxStudents")
    public ResponseEntity<CourseResponseDTO> updateTeacher(@PathVariable UUID id, @RequestBody Integer newMax){
        final CourseResponseDTO course = service.updateMaxStudents(id, newMax);
        return ok(course);
    }

    //8️⃣ PUT - Enroll a new student
    @PutMapping("/{id}/enroll")
    public ResponseEntity<CourseResponseDTO> enrollStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDto){
        final CourseResponseDTO course = service.enrollStudent(id, studentDto);
        return ok(course);
    }

    //9️⃣ PUT - Remove a student from course
    @PutMapping("/{id}/remove")
    public ResponseEntity<CourseResponseDTO> removeStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDto){
        final CourseResponseDTO course = service.removeStudent(id, studentDto);
        return ok(course);
    }
}
