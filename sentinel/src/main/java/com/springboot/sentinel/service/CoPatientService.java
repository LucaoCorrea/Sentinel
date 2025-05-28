package com.springboot.sentinel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.repository.CoPatientRepository;

@Service
public class CoPatientService {

    @Autowired
    private CoPatientRepository coPatientRepository;

    public CoPatient saveCoPatient(CoPatient coPatient) {
        return coPatientRepository.save(coPatient);
    }

}
