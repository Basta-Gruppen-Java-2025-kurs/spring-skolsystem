package com.bastagruppen.springskolsystem.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import com.bastagruppen.springskolsystem.service.interfaces.CourseServiceInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseService implements CourseServiceInterface{

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourses(){
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(final UUID id){
        return courseRepository.findById(id)
            .orElseThrow(RuntimeException::new); //TODO - Add custom exception
    }

    @Override
    public Course getCourseByTitle(final String title){
        return courseRepository.findAll()
            .stream()
            .filter(c -> c.getTitle().equals(title))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    @Override
    public Course createCourse(final Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course deleteCourse(final UUID id){
        if(id == null){return null;}
        final Course course = getCourseById(id);

        try {courseRepository.delete(course);}
        catch (Exception e) {throw new RuntimeException("Could not delete course!");}//TODO - Add custom exception
        finally{
            System.out.println(
                "Deleted course -- ID: %s, Title: %s"
                .formatted(course.getId(), course.getTitle())
            );
        }

        return course;
    }

    @Override
    public Course updateTeacher(final UUID id, final String teacher){
        /*
            TODO - Will mutating course here really work as intended? 🤔
            Might need to create a new Object
        */
        final Course course = getCourseById(id);
        course.updateTeacher(teacher);
        return courseRepository.save(course);
    }

    @Override
    public Course updateMaxStudents(final UUID id, final Integer newMax){
        final Course course = getCourseById(id);
        course.updateMaxStudents(newMax);
        return courseRepository.save(course);
    }

    @Override
    public Course enrollStudent(final UUID id, StudentDTO student){
        final Course course = getCourseById(id);
        course.enrollStudent(student);
        return courseRepository.save(course);
    }

    @Override
    public Course removeStudent(final UUID id, StudentDTO student){
        final Course course = getCourseById(id);
        course.removeStudent(student);
        return courseRepository.save(course);
    }


}
