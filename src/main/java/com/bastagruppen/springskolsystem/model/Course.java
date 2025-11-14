package com.bastagruppen.springskolsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String teacher;

    @Column(nullable = false)
    private int maxStudents;

    @OneToMany(mappedBy = "course", cascade = ALL, orphanRemoval = true)
    private final Set<Enrollment> enrollments = new HashSet<>();

    public static Course create(String title, String teacher, int maxStudents) {
        return new Course(null, title, teacher, maxStudents);
    }
}
