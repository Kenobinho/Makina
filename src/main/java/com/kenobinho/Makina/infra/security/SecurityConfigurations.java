package com.kenobinho.Makina.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {


                    // qualquer um pode tentar o login
                req.requestMatchers("/login").permitAll();

                // somente o admin pode cadastrar os tecnicos
                req.requestMatchers("/cadastro").hasRole("ADMIN");

                // Qualquer cargo pode dar um get
                req.requestMatchers(HttpMethod.GET, "/equipamentos/**").hasAnyRole("ADMIN", "TECNICO", "USUARIO");
                req.requestMatchers(HttpMethod.GET, "/ocorrencias/**").hasAnyRole("ADMIN", "TECNICO", "USUARIO");

                req.requestMatchers(HttpMethod.POST, "/equipamentos/**").hasAnyRole("ADMIN", "TECNICO");
                req.requestMatchers(HttpMethod.POST, "/ocorrencias/**").hasAnyRole("ADMIN", "TECNICO");
                req.requestMatchers(HttpMethod.PATCH, "/ocorrencias/**").hasAnyRole("ADMIN", "TECNICO");



                req.anyRequest().hasRole("ADMIN"); // o restante so passa se for admin
                })
                //filtro do token antes
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}