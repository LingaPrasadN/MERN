package com.t4.app_backend.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.t4.app_backend.Entity.Otp;
import com.t4.app_backend.Repository.OtpRepository;
import com.t4.app_backend.Util.OtpUtil;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpVerificationRepository;

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailService emailService;

    public void generateAndSendOtp(String email) {
        String otp = otpUtil.generateOtp();
        Otp otpVerification = new Otp();
        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setUsed(false);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpVerificationRepository.save(otpVerification);
        // emailService.sendOtp(email, otp);
    }

    public boolean verifyOtp(String email, String inputOtp) {
        Otp otpVerification = otpVerificationRepository.findTopByEmailAndUsedFalseOrderByExpiryTimeDesc(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));
        if (!otpVerification.isUsed() && otpVerification.getExpiryTime().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("OTP expired");

        }

        if (otpVerification.getOtp().equals(inputOtp)) {
            otpVerification.setUsed(true);
            otpVerificationRepository.save(otpVerification);
            return true;
        }

        return false;
    }
}