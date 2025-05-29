package com.springboot.sentinel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sentinel.model.PatientExam;

@Repository
public interface PatientExamRepository extends JpaRepository<PatientExam, Long> {
    List<PatientExam> findByPatientId(Long patientId);

    List<PatientExam> findByCoPatientId(Long coPatientId);

}