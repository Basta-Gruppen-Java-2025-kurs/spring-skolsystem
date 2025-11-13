package com.bastagruppen.springskolsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EnrollmentRequestDTO {
    @NotNull(message = "student ID cannot be null")
    private UUID studentId;

    @NotNull(message = "course ID cannot be null")
    private Long courseId;

    public EnrollmentRequestDTO(UUID studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrollmentRequestDTO() {}
}
