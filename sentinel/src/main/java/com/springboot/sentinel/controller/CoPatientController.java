package com.springboot.sentinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.service.CoPatientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/copatients")
public class CoPatientController {
    @Autowired
    private CoPatientService coPatientService;

    @PostMapping("/register")
    public ResponseEntity<CoPatient> register(
            @RequestParam Long patientId,
            @RequestBody CoPatient coPatient) {
        CoPatient saved = coPatientService.saveCoPatient(coPatient, patientId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<CoPatient>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(coPatientService.listByPatientId(patientId));
    }
}
