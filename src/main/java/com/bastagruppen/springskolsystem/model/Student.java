package com.bastagruppen.springskolsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static com.bastagruppen.springskolsystem.util.RegexUtil.emailMatcher;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@Getter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public final class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, updatable = false)
    String name;

    @Column(nullable = false)
    int age;

    // TODO: Should maybe be 'updatable = false'?
    @Column(unique = true, nullable = false)
    String email;

    private static final int MAX_AGE = 125;
    private static final int MIN_NAME_LENGTH = 3;

    public static Student register(final String name, final int age, final String email) {
        validateName(name);

        if (age < 0 || age > MAX_AGE)
            throw new IllegalArgumentException(format("Age must be between 0 and %d", MAX_AGE));

//        validateEmail(email);

        return new Student(null, name, age, email);
    }

    public void updateName(String name) {
        validateName(name);
        this.name = name;
    }

    public void updateEmail(String email) {
//        validateEmail(email);
        this.email = email;
    }

    private static void validateName(final String name) {
        if (name == null || name.isBlank() || name.length() < MIN_NAME_LENGTH)
            throw new IllegalArgumentException(
                    format("Name must be at least %d characters long and not blank", MIN_NAME_LENGTH));
    }

    private static void validateEmail(final String email) {
        if (!emailMatcher(email))
            throw new IllegalArgumentException("Email is not valid");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student other)) return false;
        return ((id != null) && (id.equals(other.id)));
    }

    @Override
    public int hashCode() {
        return ((id != null) ? id.hashCode() : 0);
    }
}