package com.springboot.sentinel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.service.CoPatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/copatients")
@RequiredArgsConstructor
public class CoPatientController {

    private final CoPatientService coPatientService;

    @PostMapping("/register/{patientId}")
    public ResponseEntity<CoPatient> register(@RequestBody @Valid CoPatient coPatient, @PathVariable Long patientId) {
        CoPatient saved = coPatientService.saveCoPatient(coPatient, patientId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<CoPatient>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(coPatientService.listByPatientId(patientId));
    }

    @GetMapping("/by-patients/coPatient")
    public ResponseEntity<List<CoPatient>> getAllCoPatient(){
        return ResponseEntity.ok(coPatientService.findAll());
    }
    

    @DeleteMapping("/by-patient/{coPatientId}")
    public ResponseEntity<List<CoPatient>> deleteById(@PathVariable Long coPatientId) {
        coPatientService.deleteById(coPatientId);
        return ResponseEntity.noContent().build();
    }

}
