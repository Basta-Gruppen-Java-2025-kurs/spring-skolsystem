package com.bastagruppen.springskolsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequestDTO {
    @NotNull(message = "student ID cannot be null")
    private Long studentId;

    @NotNull(message = "course ID cannot be null")
    private Long courseId;

    public EnrollmentRequestDTO(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrollmentRequestDTO() {}
}
