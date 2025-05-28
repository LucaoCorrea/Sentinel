package com.springboot.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.sentinel.model.CoPatient;
import com.springboot.sentinel.service.CoPatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/copatients")
public class CoPatientController {
    @Autowired
    private CoPatientService coPatientService;

    @PostMapping
    public CoPatient saveCoPatient(@RequestBody CoPatient coPatient) {
        return coPatientService.saveCoPatient(coPatient);
    }
}
