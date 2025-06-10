package com.facturation.backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String mail;
    private String password;
}
