package com.springboot.sentinel.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.model.Patient;
import com.springboot.sentinel.model.PatientExam;
import com.springboot.sentinel.repository.CoPatientRepository;
import com.springboot.sentinel.repository.ExamRepository;
import com.springboot.sentinel.repository.PatientExamRepository;
import com.springboot.sentinel.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientExamService {

    private final PatientExamRepository patientExamRepository;
    private final PatientRepository patientRepository;
    private final ExamRepository examRepository;
    private final CoPatientRepository coPatientRepository;

    @Transactional
    public PatientExam registerExamForPatient(Long patientId, Long examId, LocalDate examDate) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exame não encontrado"));

        BigDecimal examPrice = exam.getPrice();
        BigDecimal patientCredits = patient.getCredits() != null ? patient.getCredits() : BigDecimal.ZERO;

        if (patientCredits.compareTo(examPrice) < 0) {
            throw new RuntimeException("Créditos insuficientes do paciente.");
        }

        patient.setCredits(patientCredits.subtract(examPrice));
        patientRepository.save(patient);

        PatientExam patientExam = new PatientExam();
        patientExam.setPatient(patient);
        patientExam.setExam(exam);
        patientExam.setExamDate(examDate);

        return patientExamRepository.save(patientExam);
    }

    @Transactional
    public PatientExam registerExamForCoPatient(Long coPatientId, Long examId, LocalDate examDate) {
        CoPatient coPatient = coPatientRepository.findById(coPatientId)
                .orElseThrow(() -> new EntityNotFoundException("Co-paciente não encontrado"));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exame não encontrado"));

        Patient titular = coPatient.getPatient();
        if (titular == null) {
            throw new RuntimeException("Co-paciente não vinculado a um titular.");
        }

        BigDecimal examPrice = exam.getPrice();
        BigDecimal titularCredits = titular.getCredits() != null ? titular.getCredits() : BigDecimal.ZERO;

        if (titularCredits.compareTo(examPrice) < 0) {
            throw new RuntimeException("Créditos insuficientes do titular.");
        }

        titular.setCredits(titularCredits.subtract(examPrice));
        patientRepository.save(titular);

        PatientExam patientExam = new PatientExam();
        patientExam.setPatient(titular);
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
