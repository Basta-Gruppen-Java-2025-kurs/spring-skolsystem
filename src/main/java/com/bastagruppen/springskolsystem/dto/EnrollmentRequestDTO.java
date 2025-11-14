package com.bastagruppen.springskolsystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EnrollmentRequestDTO(
        @NotNull(message = "student ID cannot be null")
        UUID studentId,

        @NotNull(message = "course ID cannot be null")
        UUID courseId,

        @NotNull(message = "Grade cannot be null")
        @Min(value = 0, message = "Grade cannot be negative")
        @Max(value = 100, message = "Grade cannot be greater than 100")
        Integer grade,

        @PastOrPresent(message = "date cannot be in the future")
        LocalDate date) {
}