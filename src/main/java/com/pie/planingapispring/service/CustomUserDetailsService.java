package com.pie.planingapispring.service;


import com.pie.planingapispring.entity.CustomUserDetails;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        if (!user.get().isActivate()) {
            throw new UsernameNotFoundException("User Not Activate");
        }

        return new CustomUserDetails(user.get());
    }
}