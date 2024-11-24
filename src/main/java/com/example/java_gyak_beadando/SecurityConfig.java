package com.example.java_gyak_beadando;

import com.example.java_gyak_beadando.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig {
    @Autowired
    private UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(
                                auth -> auth
                                        // Allow public access to static resources
                                        .requestMatchers(
                                                "/resources/**",
                                                "/static/**",
                                                "/assets/**",
                                                "/images/**",
                                                "/login",
                                                "/register",
                                                "/contact",
                                                "/contact/submit",
                                                "/contact/submit/*",
                                                "/messages",
                                                "/api/**")
                                        .permitAll()
                                        // Protect the other routes
                                        .requestMatchers("/","/lotto-query", "/lotto-query/*").authenticated()
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        )
                        .formLogin(
                                form -> form
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/",true)
                                        .permitAll()
                        ).logout(
                                logout -> logout
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        .logoutSuccessUrl("/")
                                        .permitAll()
                        );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}