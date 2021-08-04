package com.example.testsbsecurity.controller;

import com.example.testsbsecurity.model.CustomUserDetails;
import com.example.testsbsecurity.repository.AuthorityRepository;
import com.example.testsbsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private Environment env;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity home() {
//        var principal = SecurityContextHolder.getContext().getAuthentication();
//        if(principal.getClass() ==CustomUserDetails.class)
//        var lista = new ArrayList<>();
//        lista.add(userRepository.findAll());
//        lista.add(authorityRepository.findAll());

        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/tmp")
    @ResponseBody
    public String tmp() {
        return "TMP";
    }

}
