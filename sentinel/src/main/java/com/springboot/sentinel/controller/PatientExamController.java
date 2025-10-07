package com.springboot.sentinel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.request.RegisterExamRequest;
import com.springboot.sentinel.service.PatientExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patient-exams")
@RequiredArgsConstructor
public class PatientExamController {

    private final PatientExamService patientExamService;

    @PostMapping("/register/patient")
    public ResponseEntity<PatientExam> registerExamForPatient(@RequestBody RegisterExamRequest request) {
        LocalDate parsedDate = LocalDate.parse(request.getExamDate());
        PatientExam savedExam = patientExamService.registerExamForPatient(
                request.getPatientId(),
                request.getExamId(),
                parsedDate);
        return ResponseEntity.ok(savedExam);
    }

    @PostMapping("/register/copatient")
    public ResponseEntity<PatientExam> registerExamForCoPatient(@RequestBody RegisterExamRequest request) {
        LocalDate parsedDate = LocalDate.parse(request.getExamDate());
        PatientExam savedExam = patientExamService.registerExamForCoPatient(
                request.getCoPatientId(),
                request.getExamId(),
                parsedDate);
        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientExam>> getExamsByPatient(@PathVariable Long patientId) {
        List<PatientExam> exams = patientExamService.getExamsByPatient(patientId);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/copatient/{coPatientId}")
    public ResponseEntity<List<PatientExam>> getExamsByCoPatient(@PathVariable Long coPatientId) {
        List<PatientExam> exams = patientExamService.getExamsByCoPatient(coPatientId);
        return ResponseEntity.ok(exams);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientExam(@PathVariable Long id) {
        patientExamService.deletePatientExam(id);
        return ResponseEntity.noContent().build();
    }
}
