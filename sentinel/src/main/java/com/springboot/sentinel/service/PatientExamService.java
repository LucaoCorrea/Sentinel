package com.springboot.sentinel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.repository.CoPatientRepository;
import com.springboot.sentinel.repository.ExamRepository;
import com.springboot.sentinel.repository.PatientExamRepository;
import com.springboot.sentinel.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientExamService {

    private final PatientRepository patientRepository;
    private final CoPatientRepository coPatientRepository;
    private final ExamRepository examRepository;
    private final PatientExamRepository patientExamRepository;

    public PatientExam registerExamForPatient(Long patientId, Long examId, LocalDate examDate) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado."));

        if (patient.getCredits().compareTo(exam.getPrice()) < 0) {
            throw new RuntimeException("Créditos insuficientes.");
        }

        patient.setCredits(patient.getCredits().subtract(exam.getPrice()));
        patientRepository.save(patient);

        PatientExam patientExam = new PatientExam();
        patientExam.setPatient(patient);
        patientExam.setExam(exam);
        patientExam.setExamDate(examDate);

        return patientExamRepository.save(patientExam);
    }

    public PatientExam registerExamForCoPatient(Long coPatientId, Long examId, LocalDate examDate) {
        CoPatient coPatient = coPatientRepository.findById(coPatientId)
                .orElseThrow(() -> new RuntimeException("Co-fornecedor não encontrado."));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado."));

        Patient patient = coPatient.getPatient();

        if (patient.getCredits().compareTo(exam.getPrice()) < 0) {
            throw new RuntimeException("Créditos insuficientes do titular.");
        }

        patient.setCredits(patient.getCredits().subtract(exam.getPrice()));
        patientRepository.save(patient);

        PatientExam patientExam = new PatientExam();
        patientExam.setPatient(patient);
        patientExam.setCoPatient(coPatient);
        patientExam.setExam(exam);
        patientExam.setExamDate(examDate);

        return patientExamRepository.save(patientExam);
    }

    public List<PatientExam> getExamsByPatient(Long patientId) {
        return patientExamRepository.findByPatientId(patientId);
    }

    public List<PatientExam> getExamsByCoPatient(Long coPatientId) {
        return patientExamRepository.findByCoPatientId(coPatientId);
    }
}
