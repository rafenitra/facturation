package com.facturation.backend.service;


import com.facturation.backend.dto.RegisterRequest;
import com.facturation.backend.entity.User;
import com.facturation.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Création d'un utilisateur
    public User register(RegisterRequest registerRequest){ //RegisterRequest est un DTO d'user
        if(userRepository.existsByMail(registerRequest.getMail())){
            throw new RuntimeException("Email déjà utilisé");
        }
        User user = User.builder().name(registerRequest.getName())
                .mail(registerRequest.getMail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        return userRepository.save(user);
    }
}
