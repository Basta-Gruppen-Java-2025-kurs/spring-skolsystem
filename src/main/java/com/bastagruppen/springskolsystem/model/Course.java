package com.bastagruppen.springskolsystem.model;

import jakarta.persistence.*;
import lombok.*;

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
/*    @Setter(AccessLevel.NONE)
    private final List<Student> students = new ArrayList<>(); // do we actually need this?*/

}
