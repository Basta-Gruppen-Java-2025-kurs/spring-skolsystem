package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.dto.StudentEnrollmentSummaryDTO;
import com.bastagruppen.springskolsystem.model.Enrollment;
import com.bastagruppen.springskolsystem.model.Student;

import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;

public final class StudentMapper {

    public static StudentDTO toDTO(final Student entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .courses(toSummaryDTO(entity.getEnrollments()))
                .build();
    }

    public static List<StudentEnrollmentSummaryDTO> toSummaryDTO(final Set<Enrollment> enrollments) {
        return enrollments.stream()
                .map(StudentEnrollmentSummaryDTO::toSummaryDTO)
                .sorted(comparing(StudentEnrollmentSummaryDTO::course))
                .toList();
    }
}