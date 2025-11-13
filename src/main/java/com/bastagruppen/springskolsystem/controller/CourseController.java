package com.bastagruppen.springskolsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import static com.bastagruppen.springskolsystem.mapper.CourseMapper.fromDTO;
import static com.bastagruppen.springskolsystem.mapper.CourseMapper.toDTO;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.service.CourseService;
import com.bastagruppen.springskolsystem.util.ICreate;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    //1️⃣ GET - Fetch all courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        final List<CourseDTO> courses = courseService.getAllCourses();
        return ok(courses);
    }

    //2️⃣ GET - Fetch course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable UUID id){
        final Course course = courseService.getCourseById(id);
        return ok(toDTO(course));
    }

    //3️⃣ GET - Fetch course by Title
    @GetMapping(params = "title")
    public ResponseEntity<CourseDTO> getCourseByTitle(@RequestParam String title){
        final Course course = courseService.getCourseByTitle(title);
        return ok(toDTO(course));
    }

    //4️⃣ POST - Create course
    @PostMapping
    @Validated(ICreate.class)
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        final Course course = courseService.createCourse(fromDTO(courseDTO));
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(course.getId())
            .toUri();
        
            return created(uriLocation).body(toDTO(course));

    }

    //5️⃣ DEL - Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable UUID id){
        Course course = courseService.deleteCourse(id);
        return ok(toDTO(course));
    }

    //6️⃣ PUT - Update Teacher
    @PutMapping("/{id}/updateTeacher")
    public ResponseEntity<CourseDTO> updateTeacher(@PathVariable UUID id, @RequestBody String teacher){
        final Course course = courseService.updateTeacher(id, teacher);
        return ok(toDTO(course));
    }

    //7️⃣ PUT - Update Max Students
    @PutMapping("/{id}/updateMaxStudents")
    public ResponseEntity<CourseDTO> updateTeacher(@PathVariable UUID id, @RequestBody Integer newMax){
        final Course course = courseService.updateMaxStudents(id, newMax);
        return ok(toDTO(course));
    }

    //8️⃣ PUT - Enroll a new student
    @PutMapping("/{id}/enroll")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDto){
        final Course course = courseService.enrollStudent(id, studentDto);
        return ok(toDTO(course));
    }

    //9️⃣ PUT - Remove a student from course
    @PutMapping("/{id}/remove")
    public ResponseEntity<CourseDTO> removeStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDto){
        final Course course = courseService.removeStudent(id, studentDto);
        return ok(toDTO(course));
    }
}
