package com.example.bookapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth

                // public APIs
                .requestMatchers("/auth/**").permitAll()

                // admin only
                .requestMatchers("/books/delete/**").hasRole("ADMIN")

                // user + admin
                .requestMatchers("/books/**").hasAnyRole("USER", "ADMIN")

                // everything else
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form.disable());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}