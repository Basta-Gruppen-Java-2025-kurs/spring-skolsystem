package com.bastagruppen.springskolsystem.dto;

import com.bastagruppen.springskolsystem.model.Enrollment;

import java.time.LocalDate;

public record StudentEnrollmentSummaryDTO(
        String course,
        Integer grade,
        LocalDate enrollmentDate) {

    public static StudentEnrollmentSummaryDTO toSummaryDTO(final Enrollment enrollment) {
        return new StudentEnrollmentSummaryDTO(
                enrollment.getCourse().getTitle(),
                enrollment.getGrade(),
                enrollment.getDate()
        );
    }
}