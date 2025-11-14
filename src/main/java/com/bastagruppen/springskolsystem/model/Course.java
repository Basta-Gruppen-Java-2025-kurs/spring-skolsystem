package com.bastagruppen.springskolsystem.model;

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

    private final List<Student> enrolledStudents = new ArrayList<>();

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

    public void removeStudent(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null ... duh! 🙄");
        }
        this.enrolledStudents.removeIf(s -> s.getId().equals(student.getId()));
    }

    public void enrollStudent(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null ... duh! 🙄");
        }
        this.enrolledStudents.add(student);
    }
}
