package com.bastagruppen.springskolsystem.model;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public final class Course{

    protected final UUID id = java.util.UUID.randomUUID();

    @NotBlank(message = "⚠️ Title is required!")
    @Size(min = 3, max = 255, message = "⚠️ Title must be 3-255 characters!")
    final String title;

    @NotBlank(message = "⚠️ A teacher (String) must be provided for the course!")
    @Size(min = 2, max = 120, message = "⚠️ Teacher's name must be between 2-120 characters!")
    String teacher;

    @PositiveOrZero(message = "Max Students cannot be negative... you donkey! 🫏")
    @Max(value = 255, message = "Can only enroll a maximum of 255 students!")
    Integer maxStudents;

    // TODO - Add lombok validation constraints!
    final List<Student> enrolledStudents;

    public UUID getId(){return id;}
    public String getTitle(){return title;}
    public String getTeacher(){return teacher;}
    public Integer getMaxStudents(){return maxStudents;}
    public List<Student> getEnrolledStudents(){return enrolledStudents;}
    
    public static Course create(
        final String title,
        final String teacher,
        final Integer maxStudents,
        final List<Student> enrolledStudents
    ){
        return new Course(title, teacher, maxStudents, enrolledStudents);
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
