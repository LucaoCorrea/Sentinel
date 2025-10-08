package com.springboot.sentinel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.repository.ExamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamRepository examRepository;

    @PostMapping
    public ResponseEntity<Exam> registerExam(@RequestBody Exam exam) {
        Exam savedExam = examRepository.save(exam);
        return ResponseEntity.ok(savedExam);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExam() {
        return ResponseEntity.ok(examRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado com id: " + id));

        exam.setName(examDetails.getName());
        exam.setPrice(examDetails.getPrice());

        Exam updatedExam = examRepository.save(exam);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        examRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
