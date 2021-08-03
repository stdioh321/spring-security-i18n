package com.example.testsbsecurity;

import com.example.testsbsecurity.model.Authority;
import com.example.testsbsecurity.model.User;
import com.example.testsbsecurity.repository.AuthorityRepository;
import com.example.testsbsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class Main {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void doSomethingAfterStartup() {
        if (authorityRepository.count() < 1){
            var a = new Authority();
            a.setAuthority("ROLE_USER");
            authorityRepository.save(a);
        }
        if (userRepository.count() < 1) {
            Optional<Authority> currAuth = authorityRepository.findByAuthority("ROLE_USER");

            var u = User.builder()
                    .username("test")
                    .password(encoder.encode("test"))
                    .authorities(Collections.singletonList(currAuth.get()))
                    .build();

            var tmp= userRepository.save(u);
        }
        System.out.println("hello world, I have just started up");
    }
}

