package com.bastagruppen.springskolsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseRequestDTO {
    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be empty")
    private String title;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be empty")
    private String teacher;

    @Positive(message = "must be positive")
    private int maxStudents;

    public CourseRequestDTO(String title, String teacher, int maxStudents) {
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    }

    public CourseRequestDTO() {
    }
}
