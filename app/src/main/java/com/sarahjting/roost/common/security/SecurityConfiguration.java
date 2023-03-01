package com.sarahjting.roost.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .csrf().disable() // we disable this as the api needs to be accessible outside of the browser
            .authorizeRequests(authorize -> authorize.anyRequest().permitAll()) // we can secure the API endpoints on the method level
            .formLogin(Customizer.withDefaults()) // this sets up the login & logout forms
            .httpBasic(Customizer.withDefaults()); // this allows authorization with a Basic token
        return http.build();
    }
}
