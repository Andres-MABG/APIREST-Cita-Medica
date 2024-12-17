package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.servicios.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider()) // Registra el DaoAuthenticationProvider
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para todas las rutas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/pacientes/**").hasRole("PACIENTE")
                .requestMatchers("/api/medicos/**").hasRole("MEDICO")
                .requestMatchers("/api/pagos/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(); // Configura autenticación básica
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt para manejar contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Maneja la autenticación
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService); // Vincula tu UserDetailsService
        provider.setPasswordEncoder(passwordEncoder()); // Configura el codificador de contraseñas
        return provider;
    }
}
