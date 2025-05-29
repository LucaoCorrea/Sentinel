package com.springboot.sentinel.model;


import jakarta.persistence.*;
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

    private String cpf;

    private String address;

    @ManyToOne
    private Patient patient;

}
