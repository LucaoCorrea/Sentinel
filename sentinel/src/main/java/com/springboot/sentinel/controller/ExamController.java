package com.springboot.sentinel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.sentinel.model.Exam;
import com.springboot.sentinel.repository.ExamRepository;

import ch.qos.logback.core.joran.conditional.IfAction;
import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@Data
public class ExamController {

    private ExamRepository examRepository;

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
