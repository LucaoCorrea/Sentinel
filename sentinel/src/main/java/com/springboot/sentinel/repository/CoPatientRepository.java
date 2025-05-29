package com.springboot.sentinel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sentinel.model.CoPatient;

@Repository
public interface CoPatientRepository extends JpaRepository<CoPatient, Long> {
    List<CoPatient> findByPatient_Id(Long patientId);
}