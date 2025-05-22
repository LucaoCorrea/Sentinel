package com.springboot.sentinel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sentinel.model.Exam;

@Repository

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findById(Long id);
}