package com.example.restapikey.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Lee Taesung
 * @since 2022/10/11
 */

@Configuration
public class SecurityConfig {

    @Value("${rest-api-key.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${rest-api-key.http.auth-token}")
    private String principalRequestValue;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!principalRequestValue.equals(principal))
                throw new BadCredentialsException("The API key was not found or not the expected value.");

            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .antMatcher("/api/**") // 특정 경로 이하로 설정
                .cors().disable() // cors 해제
                .csrf().disable() // csrf 해제
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable(); // 로그인 폼 해제

        return http.build();
    }
}
