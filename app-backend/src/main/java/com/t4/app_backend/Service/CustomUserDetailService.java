package com.t4.app_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.RegisterUser;
import com.t4.app_backend.Repository.RegisterUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        RegisterUser user = registerUserRepository.findByUsername(username);

        return new CustomUserDetails(user.getUsername(), user.getPassword());
    }

}
