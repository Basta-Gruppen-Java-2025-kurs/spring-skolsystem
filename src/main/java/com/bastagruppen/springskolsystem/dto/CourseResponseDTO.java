package com.bastagruppen.springskolsystem.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseResponseDTO(
        UUID id,
        String title,
        String teacher,
        Integer maxStudents) {
}