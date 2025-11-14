package com.bastagruppen.springskolsystem.dto;

import com.bastagruppen.springskolsystem.util.ICreate;
import com.bastagruppen.springskolsystem.util.IUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Builder
public record StudentDTO(
        @Null(groups = ICreate.class, message = "ID must be null for new students")
        @NotNull(groups = IUpdate.class, message = "ID is required for updating students")
        UUID id,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 255, message = "Name must be 3–255 characters")
        String name,

        @PositiveOrZero(message = "Age cannot be negative")
        @Max(value = 125, message = "Age cannot be greater than 125")
        Integer age,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,

        @JsonInclude(NON_EMPTY)
        List<@Valid StudentEnrollmentSummaryDTO> courses) {
}