package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends InMemoryRepository<Course> {
}
