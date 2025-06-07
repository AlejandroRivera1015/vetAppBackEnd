package com.backend.vetApp.Config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.net.http.HttpClient;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig  {


    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/user/create").hasAnyAuthority("admin", "receptionist")
                        .requestMatchers("/appointments/get").hasAnyAuthority("admin", "doctor", "receptionist", "client")
                        .requestMatchers("/appointments/create").hasAnyAuthority("admin", "doctor")
                        .requestMatchers("/appointments/update").hasAnyAuthority("admin", "doctor")
                        .requestMatchers("/appointments/delete").hasAnyAuthority("admin", "doctor","client")
                        .requestMatchers("/doctor/**").hasAnyAuthority("admin","doctor","client","receptionist")
                        .anyRequest().denyAll()
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**").disable())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration  = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:4200","*"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return  source;

    }
}
