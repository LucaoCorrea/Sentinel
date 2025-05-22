package com.springboot.sentinel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.service.PatientExamService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/patient-exams")
@RequiredArgsConstructor
@Data
public class PatientExamController {
    private PatientExamService patientExamService;

    @PostMapping("/register")
    public ResponseEntity<PatientExam> registerExam(
            @RequestParam Long patientId,
            @RequestParam Long examId,
            @RequestParam String examDate) {

        LocalDate parsedDate = LocalDate.parse(examDate);
        PatientExam savedExam = patientExamService.registerExam(patientId, examId, parsedDate);

        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientExam>> getExamsByPatient(@PathVariable Long patientId) {
        List<PatientExam> exams = patientExamService.getExamsByPatient(patientId);
        return ResponseEntity.ok(exams);
    }

}
