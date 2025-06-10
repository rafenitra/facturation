package com.facturation.backend.controller;

import com.facturation.backend.dto.LoginRequest;
import com.facturation.backend.dto.RegisterRequest;
import com.facturation.backend.entity.User;
import com.facturation.backend.dto.JwtResponse;
import com.facturation.backend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

   @PostMapping("/login")
   public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(response);
   }
}
