package com.t4.app_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.t4.app_backend.DTO.LoginDTO;
import com.t4.app_backend.DTO.RegisterDTO;
import com.t4.app_backend.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        return authService.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return authService.loginUser(loginDTO);
    }

}