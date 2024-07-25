package com.sounshop.ShopManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sounshop.ShopManagement.service.AdminDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Initializing SecurityFilterChain");
      
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/", "/css/**", "/imgs/**", "/js/**").permitAll()
                .anyRequest().authenticated())
            .formLogin((form) -> form
                .loginPage("/login")
                .usernameParameter("login_id")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll())
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll())
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/login", "/", "/css/**", "/imgs/**", "/js/**"))
            .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable())); 
   
        return http.build();
    }

    @Bean 
    public UserDetailsService userDetailsService() {
        System.out.println("Initializing UserDetailsService");
        return new AdminDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
