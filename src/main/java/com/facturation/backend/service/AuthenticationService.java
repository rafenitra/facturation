package com.facturation.backend.service;


import com.facturation.backend.dto.LoginRequest;
import com.facturation.backend.dto.RegisterRequest;
import com.facturation.backend.entity.User;
import com.facturation.backend.repository.UserRepository;
import com.facturation.backend.dto.JwtResponse;
import com.facturation.backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
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


    public JwtResponse login(LoginRequest request){
        User user = userRepository.findByMail(request.getMail())
                .orElseThrow(()-> new RuntimeException("Utilisateur introuvable"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Mot de passe invalide");
        }
        String token = jwtUtils.generateToken(request.getMail());
        return  new JwtResponse(token);
    }
}
