package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.EnrollmentResponseDTO;
import com.bastagruppen.springskolsystem.model.Enrollment;

public final class EnrollmentMapper {

    public static EnrollmentResponseDTO toResponseDTO(Enrollment enrollment) {
        return new EnrollmentResponseDTO(
                enrollment.getId(),
                enrollment.getStudent().getName(),
                enrollment.getCourse().getTitle(),
                enrollment.getGrade(),
                enrollment.getDate()
        );
    }
}
