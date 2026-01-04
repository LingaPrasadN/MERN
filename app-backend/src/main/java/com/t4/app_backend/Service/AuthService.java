package com.t4.app_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.t4.app_backend.DTO.LoginDTO;
import com.t4.app_backend.DTO.RegisterDTO;
import com.t4.app_backend.Entity.RegisterUser;
import com.t4.app_backend.Repository.RegisterUserRepository;
import com.t4.app_backend.Util.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO) {

        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername(registerDTO.getUsername());
        registerUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        registerUserRepository.save(registerUser);

        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> loginUser(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));

        String token = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(token);

    }
}
