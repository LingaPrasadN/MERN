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
import com.t4.app_backend.DTO.VerifyRegisterOtpDTO;
import com.t4.app_backend.Entity.Otp;
import com.t4.app_backend.Entity.User;
import com.t4.app_backend.Repository.UserRepository;
import com.t4.app_backend.Util.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository registerUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO) {

        if (userRepository.findByEmail(registerDTO.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        otpService.generateAndSendOtp(registerDTO.getEmail());

        return ResponseEntity.ok("OTP sent to email. Please verify to complete registration.");
        // RegisterUser registerUser = new RegisterUser();
        // registerUser.setEmail(registerDTO.getEmail());
        // registerUser.setName(registerDTO.getName());
        // registerUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // registerUserRepository.save(registerUser);

        // return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> verifyRegisterOtp(VerifyRegisterOtpDTO verifyRegisterOtpDTO) {

        System.out.println("Verifying OTP for email: " + verifyRegisterOtpDTO.getEmail() + ", OTP: "
                + verifyRegisterOtpDTO.getOtp());
        boolean isOtpValid = otpService.verifyOtp(verifyRegisterOtpDTO.getEmail(), verifyRegisterOtpDTO.getOtp());

        if (!isOtpValid) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        User registerUser = new User();
        registerUser.setEmail(verifyRegisterOtpDTO.getEmail());
        registerUser.setName(verifyRegisterOtpDTO.getName());
        registerUser.setPassword(passwordEncoder.encode(verifyRegisterOtpDTO.getPassword()));

        registerUserRepository.save(registerUser);

        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> loginUser(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()));

        String token = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(token);

    }
}
