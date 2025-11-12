package com.bastagruppen.springskolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponseDTO {
    private long id;
    private String title;
    private String teacher;
    private int maxStudents;
}
