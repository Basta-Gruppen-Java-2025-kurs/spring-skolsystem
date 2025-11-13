package com.bastagruppen.springskolsystem.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EnrollmentResponseDTO(
        Long id,
        String studentName,
        String courseTitle,
        LocalDate enrollmentDate
) {}
