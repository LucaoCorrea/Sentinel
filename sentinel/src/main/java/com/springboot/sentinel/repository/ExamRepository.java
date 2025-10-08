package com.springboot.sentinel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.sentinel.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    // No need to redefine findById method, it's already provided by JpaRepository
    // Optional<Exam> findById(Long id); // This is redundant
}
