package com.springboot.sentinel.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.service.PatientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping
    public List<Patient> listPatient() {
        return patientService.listPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> findPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void delPatient(@PathVariable Long id) {
        patientService.delPatient(id);
    }

    @PostMapping("{id}/credits")
    public Patient upgradeCredits(@PathVariable Long id, BigDecimal credits) {
        return patientService.upgradeCredits(id, credits);
    }

}
