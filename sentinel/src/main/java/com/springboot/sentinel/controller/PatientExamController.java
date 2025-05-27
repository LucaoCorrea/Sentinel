package com.springboot.sentinel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.request.RegisterExamRequest;
import com.springboot.sentinel.service.PatientExamService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/patient-exams")
@RequiredArgsConstructor
public class PatientExamController {
    private final PatientExamService patientExamService;

    @PostMapping("/register")
    public ResponseEntity<PatientExam> registerExam(@RequestBody RegisterExamRequest request) {
        LocalDate parsedDate = LocalDate.parse(request.getExamDate());
        PatientExam savedExam = patientExamService.registerExam(
                request.getPatientId(),
                request.getExamId(),
                parsedDate);

        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientExam>> getExamsByPatient(@PathVariable Long patientId) {
        List<PatientExam> exams = patientExamService.getExamsByPatient(patientId);
        return ResponseEntity.ok(exams);
    }

}
