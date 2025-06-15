package com.facturation.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Clients")
public class Client {

    @Id()
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientId;

    private String name;

    private String mail;

    private String company;

    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
