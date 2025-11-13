package com.bastagruppen.springskolsystem.dto;

import lombok.Builder;

@Builder
public record CourseResponseDTO (
    Long id,
    String title,
    String teacher,
    Integer maxStudents) {}
