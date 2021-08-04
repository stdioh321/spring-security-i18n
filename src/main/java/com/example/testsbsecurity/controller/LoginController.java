package com.example.testsbsecurity.controller;

import com.example.testsbsecurity.config.locale.SmartLocaleResolver;
import com.example.testsbsecurity.dto.LoginForm;
import com.example.testsbsecurity.model.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private SmartLocaleResolver smartLocaleResolver;

    @GetMapping
    public String index(LoginForm loginForm) {
        return "login";
    }

    @PostMapping
    public String tryLogin(@Valid LoginForm loginForm, BindingResult result, Model model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "login";
        }
        Authentication auth = null;
        try {
            var userAuth = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
            auth = authManager.authenticate(userAuth);
            var user = (CustomUserDetails) auth.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(user.toUserPassAuthToken());
            return "redirect:/me";
        } catch (Exception ex) {
            var currLocale = LocaleContextHolder.getLocale();
            model.addAttribute("errors", Collections.singletonList(messageSource.getMessage("invalid.credentials", null, currLocale)));
            return "login";
        }
    }
}
