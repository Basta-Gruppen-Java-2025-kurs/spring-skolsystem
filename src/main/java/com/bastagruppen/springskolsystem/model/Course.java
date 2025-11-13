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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "⚠️ Title is required!")
    @Size(min = 3, max = 255, message = "⚠️ Title must be 3-255 characters!")
    private String title;

    @NotBlank(message = "⚠️ A teacher (String) must be provided for the course!")
    @Size(min = 2, max = 120, message = "⚠️ Teacher's name must be between 2-120 characters!")
    private String teacher;

    @PositiveOrZero(message = "Max Students cannot be negative... you donkey! 🫏")
    @Max(value = 255, message = "Can only enroll a maximum of 255 students!")
    private Integer maxStudents;

    // TODO - Add lombok validation constraints!
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> enrolledStudents;

    public UUID getId(){return id;}
    public String getTitle(){return title;}
    public String getTeacher(){return teacher;}
    public Integer getMaxStudents(){return maxStudents;}
    public List<Student> getEnrolledStudents(){return enrolledStudents;}

    protected Course(String title, String teacher, Integer maxStudents){
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    };
    protected Course(String title, String teacher, Integer maxStudents, List<Student> enrolledStudents){
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        this.enrolledStudents = enrolledStudents;
    }
    
    public static Course create(
        final String title,
        final String teacher,
        final Integer maxStudents
    ){
        return new Course(title, teacher, maxStudents, new ArrayList<>());
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
