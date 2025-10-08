package com.springboot.sentinel.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Patient savePatient(@RequestBody @Valid Patient patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody @Valid Patient patient) {
        return patientService.updatePatient(id, patient);
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
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @PostMapping("{id}/credits")
    public Patient updateCredits(@PathVariable Long id, @RequestBody Map<String, BigDecimal> body) {
        BigDecimal credits = body.get("credits");
        return patientService.updateCredits(id, credits);
    }

}