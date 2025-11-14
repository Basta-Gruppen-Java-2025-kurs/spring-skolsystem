package com.bastagruppen.springskolsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String teacher;

    @Column(nullable = false)
    private Integer maxStudents;

    @OneToMany(mappedBy = "course", cascade = ALL, orphanRemoval = true)
    private final Set<Enrollment> enrollments = new HashSet<>();
    // TODO - Add lombok validation constraints!
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Student> enrolledStudents = new ArrayList<>();

    protected Course(String title, String teacher, Integer maxStudents){
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    };

    public static Course create(
        final String title,
        final String teacher,
        final Integer maxStudents
    ) {
        return new Course(title, teacher, maxStudents, new ArrayList<>());
    }


    public static Course create(String title, String teacher, int maxStudents) {
        return new Course(null, title, teacher, maxStudents);
    }

    public void updateTeacher(String teacher){
        if(teacher == null){
            throw new IllegalArgumentException("Teacher cannot be empty!");
        }

        this.teacher = teacher;
    }

    public void updateMaxStudents(Integer maxStudents){
        if(maxStudents <= 0 || maxStudents > 255){
            throw new IllegalArgumentException("Max students needs to be between 1-255... you donkey! 🫏");
        }

        this.maxStudents = maxStudents;
    }

    public void removeStudent(StudentDTO studentDto){
        if(studentDto == null){
            throw new IllegalArgumentException("Student cannot be null ... duh! 🙄");
        }
        this.enrolledStudents.removeIf(s -> s.getId().equals(studentDto.id()));
    }

    public void enrollStudent(StudentDTO studentDto){
        if(studentDto == null){
            throw new IllegalArgumentException("Student cannot be null ... duh! 🙄");
        }
        this.enrolledStudents.add(Student.register(studentDto.name(), studentDto.age(), studentDto.email()));
    }
}
