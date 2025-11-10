package com.bastagruppen.springskolsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private long id;
    private String title;
    private String teacher;
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

    //region getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Student> getStudents() {
        return students;
    }
    //endregion
}
