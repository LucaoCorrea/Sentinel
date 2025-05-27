package com.springboot.sentinel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.repository.ExamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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

}
