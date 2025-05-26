package com.springboot.sentinel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.repository.ExamRepository;
import com.springboot.sentinel.repository.PatientExamRepository;
import com.springboot.sentinel.repository.PatientRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PatientExamService {

    private final PatientRepository patientRepository;
    private final ExamRepository examRepository;
    private final PatientExamRepository patientExamRepository;

    public PatientExam registerExam(Long patientId, Long examId, LocalDate localDate) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado"));

        if (patient.getCredits().compareTo(exam.getPrice()) < 0) {
            throw new RuntimeException("Créditos insuficientes");
        }

        patient.setCredits(patient.getCredits().subtract(exam.getPrice()));
        patientRepository.save(patient);

        PatientExam patientExam = new PatientExam();
        patientExam.setPatient(patient);
        patientExam.setExam(exam);
        patientExam.setExamDate(localDate);

        return patientExamRepository.save(patientExam);
    }

    public List<PatientExam> getExamsByPatient(Long patientId) {
        return patientExamRepository.findByPatientId(patientId);
    }

}
