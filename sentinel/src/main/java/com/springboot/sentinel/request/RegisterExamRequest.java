package com.springboot.sentinel.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterExamRequest {
    private Long patientId;
    private Long examId;
    private String examDate;
}
