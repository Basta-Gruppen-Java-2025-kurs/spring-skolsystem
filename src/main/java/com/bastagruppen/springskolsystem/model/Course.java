package com.bastagruppen.springskolsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Course extends Entity {
    //region getters and setters
    @Setter
    private long id;
    @Setter
    private String title;
    @Setter
    private String teacher;
    @Setter
    private int maxStudents;
    private final List<Student> students = new ArrayList<>(); // do we actually need this?

    public Course(long id, String title, String teacher, int maxStudents) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    }

    public Course() {
    }

}
