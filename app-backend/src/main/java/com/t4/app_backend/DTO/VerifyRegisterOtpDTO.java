package com.t4.app_backend.DTO;

import lombok.Data;

@Data
public class VerifyRegisterOtpDTO {
    private String email;
    private String otp;
    private String name;
    private String password;

}
