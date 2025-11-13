package com.bastagruppen.springskolsystem.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record CourseDTO (

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id,

    @NotBlank(message = "⚠️ Title is required!")
    @Size(min = 3, max = 255, message = "⚠️ Title must be 3-255 characters!")
    String title,

    @NotBlank(message = "⚠️ A teacher (String) must be provided for the course!")
    @Size(min = 2, max = 120, message = "⚠️ Teacher's name must be between 2-120 characters!")
    String teacher,

    @PositiveOrZero(message = "Max Students cannot be negative... you donkey! 🫏")
    @Max(value = 255, message = "Can only enroll a maximum of 255 students!")
    Integer maxStudents,

    @JsonIgnore
    List<StudentDTO> enrolledStudents)
{
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof CourseDTO other)) return false;
        return ((id != null) && (id.equals(other.id)));
    }

    @Override
    public int hashCode(){
        return ((id != null) ? id.hashCode() : 0);
    }
}
