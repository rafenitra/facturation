package com.facturation.backend.dto;

import lombok.Data;


@Data
public class RegisterRequest {
    private String name;
    private String mail;
    private String password;
}
