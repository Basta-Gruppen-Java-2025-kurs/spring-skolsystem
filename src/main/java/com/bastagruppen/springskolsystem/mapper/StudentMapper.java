package com.bastagruppen.springskolsystem.mapper;

import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.model.Student;

public final class StudentMapper {

    public static StudentDTO toDTO(final Student entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .build();
    }
}