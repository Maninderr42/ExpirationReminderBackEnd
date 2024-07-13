package com.example.jwtAuthentication.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterChainConfig {
 @Autowired
private  AuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilterChainConfig(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors(corsConfig->corsConfig.disable());

        httpSecurity.csrf(csrfConfig->csrfConfig.disable());

        httpSecurity.authorizeHttpRequests(
                requestMatcher->requestMatcher.requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/sign-up").permitAll()
                        .anyRequest().authenticated()
                );


        httpSecurity.exceptionHandling(
                exceptionConfig->exceptionConfig.authenticationEntryPoint
                        (authenticationEntryPoint)
        );
         httpSecurity.sessionManagement(
                 sessionConfig->sessionConfig.sessionCreationPolicy
                         (SessionCreationPolicy.STATELESS)
         );

         httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         return httpSecurity.build();

    }
}
