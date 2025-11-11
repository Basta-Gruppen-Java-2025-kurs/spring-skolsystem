package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByEmail(String email);

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