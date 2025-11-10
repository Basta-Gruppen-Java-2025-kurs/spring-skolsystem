package com.bastagruppen.springskolsystem.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static com.bastagruppen.springskolsystem.util.RegexUtil.emailMatcher;
import static lombok.AccessLevel.PRIVATE;

@SuperBuilder
@Getter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public final class Student extends Entity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be 3–255 characters")
    final String name;

    @PositiveOrZero(message = "Age cannot be negative")
    @Max(value = 125, message = "Age cannot be greater than 125")
    int age;

    // TODO: E-mail should be unique
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    String email;

    public static Student register(final String name,
                                   final int age,
                                   final String email) {
        return new Student(name, age, email);
    }

    public void updateAge(int age) {
        if (age <= this.age || age > 125)
            throw new IllegalArgumentException("Age must be greater than the current age and at most 125");

        this.age = age;
    }
