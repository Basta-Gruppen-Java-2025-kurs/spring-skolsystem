package com.bastagruppen.springskolsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EnrollmentResponseDTO(
        @JsonIgnore
        UUID id,
        String studentName,
        String courseTitle,
        Integer grade,
        LocalDate enrollmentDate) {
}