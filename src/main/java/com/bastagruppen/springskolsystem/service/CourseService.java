package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.exception.CourseNotFoundException;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import com.bastagruppen.springskolsystem.service.interfaces.CourseServiceInterface;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.bastagruppen.springskolsystem.mapper.CourseMapper.toResponseDto;
import static com.bastagruppen.springskolsystem.model.Course.create;

@Service
import com.bastagruppen.springskolsystem.dto.CourseDTO;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import com.bastagruppen.springskolsystem.service.interfaces.CourseServiceInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseService implements CourseServiceInterface {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    public Course findById(UUID id) {
        return repository.findById(id).orElseThrow(CourseNotFoundException::new);
    }


    @Override
    public List<CourseDTO> getAllCourses(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(final UUID id){
        return repository.findById(id)
            .orElseThrow(RuntimeException::new); //TODO - Add custom exception
    }

    @Override
    public Course getCourseByTitle(final String title){
        return repository.findAll()
            .stream()
            .filter(c -> c.getTitle().equals(title))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    @Override
    public Course createCourse(final Course course){
        return repository.save(course);
    }

    @Override
    public Course deleteCourse(final UUID id){
        if(id == null){return null;}
        final Course course = getCourseById(id);

        try {repository.delete(course);}
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
        return repository.save(course);
    }

    @Override
    public Course updateMaxStudents(final UUID id, final Integer newMax){
        final Course course = getCourseById(id);
        course.updateMaxStudents(newMax);
        return repository.save(course);
    }

    @Override
    public Course enrollStudent(final UUID id, Student student){
        final Course course = getCourseById(id);
        course.enrollStudent(student);
        return repository.save(course);
    }

    @Override
    public Course removeStudent(final UUID id, Student student){
        final Course course = getCourseById(id);
        course.removeStudent(student);
        return repository.save(course);
    }

    public CourseResponseDTO createCourse(final CourseRequestDTO request) {
        final Course course = create(
                request.getTitle(),
                request.getTeacher(),
                request.getMaxStudents());

        final Course saved = repository.save(course);
        return toResponseDto(saved);
    }
}