package com.example.testsbsecurity.config.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class SmartLocaleResolver extends CookieLocaleResolver {
    @Autowired
    private Locale defaultLocale;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = null;
        String langParam = request.getParameter("lang");
        String langHeader = request.getHeader("Accept-Language");
        if (langHeader != null)
            acceptLanguage = langHeader;
        if (langParam != null)
            acceptLanguage = langParam;

        if (acceptLanguage != null) {
            var newLocale = Locale.forLanguageTag(acceptLanguage);
            return newLocale;
        }

        return defaultLocale;
    }

}