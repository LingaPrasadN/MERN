package com.t4.app_backend.Repository;

import com.t4.app_backend.Entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Long> {



    RegisterUser findByEmailAndPassword(String email, String password);

    RegisterUser findByEmail(String email);

    
} 