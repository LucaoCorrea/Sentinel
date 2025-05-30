package com.springboot.sentinel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.repository.CoPatientRepository;
import com.springboot.sentinel.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoPatientService {

    private final CoPatientRepository coPatientRepository;
    private final PatientRepository patientRepository;

    public CoPatient saveCoPatient(CoPatient coPatient, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor titular não encontrado"));

        coPatient.setPatient(patient);

        return coPatientRepository.save(coPatient);
    }

    public List<CoPatient> listByPatientId(Long patientId) {
        return coPatientRepository.findByPatient_Id(patientId);
    }

}
