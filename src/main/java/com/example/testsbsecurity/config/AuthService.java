package com.example.testsbsecurity.config;

import com.example.testsbsecurity.model.CustomUserDetails;


import com.example.testsbsecurity.model.User;
import com.example.testsbsecurity.repository.AuthorityRepository;
import com.example.testsbsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(s);
        if (userOpt.isEmpty()) throw new UsernameNotFoundException("User not found.");

        CustomUserDetails customUserDetails = new CustomUserDetails(userOpt.get());
        return customUserDetails;
    }
}
