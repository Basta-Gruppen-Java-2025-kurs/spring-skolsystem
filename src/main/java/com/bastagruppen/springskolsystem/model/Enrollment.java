package com.bastagruppen.springskolsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Table(
        name = "enrollment",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"})
)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @Column(nullable = false)
    Integer grade;

    @Column(nullable = false)
    LocalDate date;

    public static Enrollment newEnrollment(final Student student,
                                           final Course course,
                                           final Integer grade,
                                           final LocalDate date) {
        return new Enrollment(null, student, course, grade, date);
    }
}
