package com.bastagruppen.springskolsystem.config;

import com.bastagruppen.springskolsystem.dto.CourseRequestDTO;
import com.bastagruppen.springskolsystem.dto.EnrollmentRequestDTO;
import com.bastagruppen.springskolsystem.dto.StudentDTO;
import com.bastagruppen.springskolsystem.service.CourseService;
import com.bastagruppen.springskolsystem.service.EnrollmentService;
import com.bastagruppen.springskolsystem.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Clock;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

@RequiredArgsConstructor
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final Clock clock;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    private boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        // --- Students ---
        var alice = studentService.registerStudent(
                new StudentDTO(null, "Alice Ginger", 22, "alice@lexicon.com", null));
        var bob = studentService.registerStudent(
                new StudentDTO(null, "Bob Doe", 24, "bob@lexicon.com", null));
        var bobby = studentService.registerStudent(
                new StudentDTO(null, "Bobby Junior", 45, "bobby@lexicon.com", null));
        var charlie = studentService.registerStudent(
                new StudentDTO(null, "Charlie Brown", 21, "charlie@lexicon.com", null));

        // --- Courses ---
        var math = courseService.createCourse(
                new CourseRequestDTO("Mathematics", "Nils Andersson", 30));
        var programming = courseService.createCourse(
                new CourseRequestDTO("Programming", "Olle Nilsson", 25));
        var history = courseService.createCourse(
                new CourseRequestDTO("History", "Anders Svensson", 4));

        // --- Enrollments ---
        // Bob: Math + Programming + History
        enrollmentService.enroll(new EnrollmentRequestDTO(
                bob.getId(), math.id(), 75, now(clock)));
        enrollmentService.enroll(new EnrollmentRequestDTO(
                bob.getId(), programming.id(), 100, of(2024, 10, 3)));
        enrollmentService.enroll(new EnrollmentRequestDTO(
                bob.getId(), history.id(), 25, now(clock)));

        // Charlie: Math + History
        enrollmentService.enroll(new EnrollmentRequestDTO(
                charlie.getId(), math.id(), 65, now(clock).minusMonths(1)));
        enrollmentService.enroll(new EnrollmentRequestDTO(
                charlie.getId(), history.id(), 85, of(2023, 12, 20)));

        // Bobby: Programming
        enrollmentService.enroll(new EnrollmentRequestDTO(
                bobby.getId(), programming.id(), 10, now(clock)));

        // Alice: History
        enrollmentService.enroll(new EnrollmentRequestDTO(
                alice.getId(), history.id(), 95, of(2022, 11, 1)));

        alreadySetup = true;
    }
}