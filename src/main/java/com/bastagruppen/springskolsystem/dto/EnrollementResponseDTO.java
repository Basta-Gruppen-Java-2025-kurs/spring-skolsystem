package com.bastagruppen.springskolsystem.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EnrollementResponseDTO(
        Long id,
        String studentName,
        String courseTitle,
        LocalDate enrollmentDate
) {}
