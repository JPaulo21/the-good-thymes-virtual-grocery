package com.jp.thymeleaf.thegoodthymesvirtualgrocery.config.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    private static final String[] PUBLIC_RESOURCES = {
            "/webjars/**",
            "/assets/**",
            "/favicon.ico",
            "/",
            "/error/**",
            "/h2-console/**",
            "/auth/**",
            "/cart/**",
            "/product/**",
            "/customers/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                //.re
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            SavedRequest savedRequest = requestCache.getRequest(request, response);
                            String targetUrl = (savedRequest != null) ? savedRequest.getRedirectUrl() : "/";

                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            PrintWriter writer = response.getWriter();
                            writer.write(String.format("{ \"url\": \"%s\" }",targetUrl));
                            writer.flush();
                        }) //
                        //.failureUrl("/customers/register") //Caso haja erro no login direciona para a url do parametro
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.setContentType("application/json");
                            PrintWriter writer = response.getWriter();
                            writer.write(String.format("{ \"message\": \"%s\" }", exception.getMessage()));
                            writer.flush();
                        })
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PUBLIC_RESOURCES).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> { headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
                })
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")  
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth/login"))
                )
                .build();
    }


    // AuthenticationManagerBuilder authenticationManagerBuilder = http
    //        .getSharedObject(AuthenticationManagerBuilder.class);
    // authenticationManager = authenticationManagerBuilder
    //        .userDetailsService(Implementação de UserDetailsService)
    //        .passwordEncoder(BCryptPasswordEncoder)
    //        .build();

    // AuthenticationConfiguration observa as classes que implementamos o UserDetailsService e a Classe de Criptografia
    // e com isso o Gerenciador de Autenticação(AuthenticationManager) será instaciado com as classes que configuramos.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
