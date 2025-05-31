package com.springboot.sentinel.model;

import com.springboot.sentinel.enums.CoPatientType;
import com.springboot.sentinel.validation.CPF;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "co_patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String city;

    private String phone;

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    private String cpf;

    private String address;

    @Enumerated(EnumType.STRING)
    private CoPatientType type;

    @ManyToOne
    private Patient patient;

}
