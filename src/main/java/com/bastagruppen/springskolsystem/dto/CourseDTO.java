package com.bastagruppen.springskolsystem.dto;

import java.util.List;
import java.util.UUID;

import com.bastagruppen.springskolsystem.model.Student;
import com.bastagruppen.springskolsystem.util.ICreate;
import com.bastagruppen.springskolsystem.util.IUpdate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record CourseDTO (
    @Null(groups = ICreate.class, message = "⚠️ ID must be null for new courses!")
    @NotNull(groups = IUpdate.class, message = "⚠️ ID is required for updating course!")
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

    // TODO - Add lombok validation constraints!
    List<Student> enrolledStudents)
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
