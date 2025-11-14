package com.bastagruppen.springskolsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CourseRequestDTO(
        @NotNull(message = "cannot be null")
        @NotBlank(message = "cannot be empty")
        String title,

        @NotNull(message = "cannot be null")
        @NotBlank(message = "cannot be empty")
        String teacher,

        @Positive(message = "must be positive")
        int maxStudents) {
}