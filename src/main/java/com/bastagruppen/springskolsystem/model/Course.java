package com.bastagruppen.springskolsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String teacher;
    @Column(nullable = false)
    private int maxStudents;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Set<Enrollment> enrollments = new HashSet<>();
/*    @Setter(AccessLevel.NONE)
    private final List<Student> students = new ArrayList<>(); // do we actually need this?*/

}
