package com.t4.app_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.User;
import com.t4.app_backend.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository registerUserRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = registerUserRepository.findByEmail(username);

        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getName());
    }

}
