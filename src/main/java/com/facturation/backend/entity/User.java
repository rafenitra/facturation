package com.facturation.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String mail;


    @Column(nullable = false)
    private String password;

    @Column(name = "inscription_date")
    @Builder.Default
    private LocalDate inscriptionDate = LocalDate.now();
    
}
