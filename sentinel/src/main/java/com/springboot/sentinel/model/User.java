package com.springboot.sentinel.model;

import com.springboot.sentinel.enums.RoleEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

   @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;


public User(String username, String email, String password, RoleEnum role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
}
}
