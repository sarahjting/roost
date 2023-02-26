package com.sarahjting.roost.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// every single book on security configuration is out of date
// just read this blog entry

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
