package com.bastagruppen.springskolsystem.service;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.CourseResponseDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.exception.CourseNotFoundException;
import com.bastagruppen.springskolsystem.mapper.CourseMapper;
import com.bastagruppen.springskolsystem.model.Course;
import com.bastagruppen.springskolsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    public Course findById(UUID id) {
        return repository.findById(id).orElseThrow(CourseNotFoundException::new);
    }


    public List<CourseResponseDTO> getAllCourses(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO getCourseById(final UUID id){
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(CourseNotFoundException::new);
    }

    public CourseResponseDTO getCourseByTitle(final String title){
        return repository.findAll()
            .stream()
            .filter(c -> c.getTitle().equals(title))
            .findFirst()
            .map(mapper::toResponseDto)
            .orElseThrow(CourseNotFoundException::new);
    }

    public CourseResponseDTO createCourse(CourseRequestDTO course){
        return mapper.toResponseDto(repository.save(mapper.toEntity(course)));
    }

    public boolean deleteCourse(final UUID id){
        if(id == null){return false;}
        final Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);

        try {repository.delete(course);}
        catch (Exception e) {throw new RuntimeException("Could not delete course!");}//TODO - Add custom exception
        finally{
            log.info(String.format("Deleted course -- ID: %s, Title: %s%n", course.getId(), course.getTitle()));
        }

        return true;
    }

    public CourseResponseDTO updateTeacher(final UUID id, final String teacher){
        /*
            TODO - Will mutating course here really work as intended? 🤔
            Might need to create a new Object
        */
        final Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);
        course.updateTeacher(teacher);
        return mapper.toResponseDto(repository.save(course));
    }

    public CourseResponseDTO updateMaxStudents(final UUID id, final Integer newMax){
        final Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);
        course.updateMaxStudents(newMax);
        return mapper.toResponseDto(repository.save(course));
    }

    public CourseResponseDTO enrollStudent(final UUID id, StudentDTO student){
        final Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);
        course.enrollStudent(student);
        return mapper.toResponseDto(repository.save(course));
    }

    public CourseResponseDTO removeStudent(final UUID id, StudentDTO student){
        final Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);
        course.removeStudent(student);
        return mapper.toResponseDto(repository.save(course));
    }

/*    public CourseResponseDTO createCourse(final CourseRequestDTO request) {
        final Course course = create(
                request.title(),
                request.teacher(),
                request.maxStudents());

        final Course saved = repository.save(course);
        return mapper.toResponseDto(saved);
    }*/
}