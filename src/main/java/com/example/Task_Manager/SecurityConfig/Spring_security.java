package com.example.Task_Manager.SecurityConfig;

import com.example.Task_Manager.Filter.JwtFilter;
import com.example.Task_Manager.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Spring_security {

    @Autowired
    private JwtFilter jwtFilter;

    private final UserDetailsServiceImpl userDetailsService;
    
    public Spring_security(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         return http
                 .authorizeHttpRequests(request -> request
                         .requestMatchers("/User/**", "/Task/**").authenticated()
                         .requestMatchers("/public/**").permitAll()
                         .requestMatchers("/admin/**").hasRole("ADMIN")
                         .anyRequest().authenticated())
                 .csrf(AbstractHttpConfigurer::disable)
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
