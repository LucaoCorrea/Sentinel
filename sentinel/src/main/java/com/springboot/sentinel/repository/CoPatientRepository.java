package com.springboot.sentinel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sentinel.model.CoPatient;

@Repository
public interface CoPatientRepository extends JpaRepository<CoPatient, Long> {
    Optional<CoPatient> findByCpf(String cpf);
}