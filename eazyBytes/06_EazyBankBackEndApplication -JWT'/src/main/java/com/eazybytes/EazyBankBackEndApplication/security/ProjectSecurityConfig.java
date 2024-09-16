package com.eazybytes.EazyBankBackEndApplication.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.eazybytes.EazyBankBackEndApplication.filter.CsrfCookieFilter;
import com.eazybytes.EazyBankBackEndApplication.filter.JWTTokenValidationFilter;
import com.eazybytes.EazyBankBackEndApplication.filter.JWTokenGenerationFilter;

import jakarta.servlet.http.HttpServletRequest;

//Here we have to make two changes inorder JWT token to work

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        // CORS
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
//Change 1 - I have setted JWT token to header named Authorization in file named JWTTokenGeneratorFilter. 
                config.setExposedHeaders(Arrays.asList("Authorization"));   
                config.setMaxAge(3600L);
                return config;
            }
        }));

        // CSRF
        // http.securityContext(context -> context.requireExplicitSave(false))
        //     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)); 
//Change 2 - since we are creating custom JWT token here we have to tell backend to not to create JSESSION id each time user logins.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//Change 3 - adding JWTokenGenerationFilter filter just after BasicAuthenticationFilter.
                .addFilterAfter(new JWTokenGenerationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class);
                            

        // Users with specific authorities in DB can only access end points
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("myAccount").hasRole("USER")       
                .requestMatchers("myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("myCards").hasRole("USER")
                .requestMatchers("myLoans").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/contact", "/notices", "/register").permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }

// storing pass word in BCrypt format inside DB
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
