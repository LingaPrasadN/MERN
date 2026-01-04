package com.t4.app_backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t4.app_backend.Entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByEmailAndUsedFalseOrderByExpiryTimeDesc(String email);

}
