package com.bastagruppen.springskolsystem.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Student;


public interface CourseServiceInterface {
    List<CourseDTO> getAllCourses();
    Course getCourseById(UUID id);
    Course getCourseByTitle(String title);
    Course createCourse(Course course);
    Course deleteCourse(UUID id);
    Course updateTeacher(UUID id, String teacher);
    Course updateMaxStudents(UUID id, Integer newMax);
    Course enrollStudent(UUID id, Student student);
    Course removeStudent(UUID id, Student student);
    
}
