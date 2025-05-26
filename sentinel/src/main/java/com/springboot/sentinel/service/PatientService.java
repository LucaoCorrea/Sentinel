package com.springboot.sentinel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sentinel.controller.PatientController;
import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.repository.PatientRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void delPatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient upgradeCredits(Long id, BigDecimal credits) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado: " + id);
        }

        Patient patient = optionalPatient.get();

        patient.setCredits(credits);
        return patientRepository.save(patient);
    }
}
