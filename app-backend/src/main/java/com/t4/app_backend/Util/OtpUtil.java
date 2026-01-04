package com.t4.app_backend.Util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();

    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
