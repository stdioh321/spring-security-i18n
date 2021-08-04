package com.example.testsbsecurity.config.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class SmartLocaleResolver implements LocaleResolver {
    @Autowired
    private Locale defaultLocale;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String langParam = request.getParameter("lang");
        if (langParam != null) {
            Locale currLocale = Locale.forLanguageTag(langParam);
            return currLocale;
        }
        String langHeader = request.getHeader("Accept-Language");
        if (langHeader != null)
            return request.getLocale();

        return defaultLocale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        System.out.println(httpServletRequest);
    }

}