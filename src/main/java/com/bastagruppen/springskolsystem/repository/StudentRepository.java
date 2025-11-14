package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("""
            SELECT s
            FROM Student s
            LEFT JOIN FETCH s.enrollments e
            LEFT JOIN FETCH e.course
            WHERE s.email = :email
            """)
    Optional<Student> findByEmail(@Param("email") String email);

    @Query("""
            SELECT DISTINCT s
            FROM Student s
            LEFT JOIN FETCH s.enrollments e
            LEFT JOIN FETCH e.course
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name,'%') )
            ORDER BY s.name
            """)
    List<Student> findByNameContainingIgnoreCaseSortedByName(@Param("name") String name);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query("""
            SELECT DISTINCT s
            FROM Student s
            LEFT JOIN FETCH s.enrollments e
            LEFT JOIN FETCH e.course
            WHERE s.age > :ageAfter
            ORDER BY s.age
            """)
    List<Student> findByAgeAfterSortedByAge(@Param("ageAfter") int ageAfter);

    @Query("SELECT COUNT(s) FROM Student s")
    long getNumberOfRegisteredStudents();

    @Modifying
    @Query("""
            UPDATE Student s
            SET s.age = :newAge
            WHERE s.id = :id
                AND :newAge > s.age
                AND :newAge <= 125
            """)
    int updateAge(@Param("id") Long id, @Param("newAge") int newAge);
}