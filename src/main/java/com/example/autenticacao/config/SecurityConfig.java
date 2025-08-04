package com.example.autenticacao.config;

import com.example.autenticacao.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // PasswordEncoder que não faz nada: aceita senha em texto puro
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UsuarioDetailsService service) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**").permitAll()
                        .anyRequest().authenticated()) // Exige autenticação para outras páginas
                .formLogin(form -> form
                        .loginPage("/templates/login.html") // Define a página de login personalizada
                        .loginProcessingUrl("/login")
                        .usernameParameter("username") // Campo de email no formulário
                        .passwordParameter("password") // Campo de senha no formulário
                        .defaultSuccessUrl("/templates/principal.html", true)
                        .permitAll()) // Permite acesso à página de login sem autenticação
                .logout(logout -> logout.permitAll()); // Permite logout sem autenticação
        return http.build();
    }
}
