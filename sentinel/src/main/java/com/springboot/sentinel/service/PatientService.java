package com.springboot.sentinel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(PatientRepository patientRepository) {
        return patientRepository.save(patientRepository);
    }
}
