package com.eazybytes.EazyBankBackEndApplication.security;

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

import jakarta.servlet.http.HttpServletRequest;

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
                config.setMaxAge(3600L);
                return config;
            }
        }));
        // cors(Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer)

        // CSRF
        http.securityContext(context -> context.requireExplicitSave(false))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)); //So with this configurations we are telling to the Spring Security framework,"Please always create the JSESSIONID after the initial login is completed."And the same JSESSIONID it is going to send to the UI application and my UI application can leverage the samefor all the subsequent requests that it is going to make after the initial login.
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        // http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

    // Cutom security configuration.
        /*
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans","/user").authenticated() - Any logined user can access these end points.
                .requestMatchers("/contact", "/notices", "/register").permitAll());
        */

        // Users with specific authorities in DB can only access end points
        
        http.authorizeHttpRequests(requests -> requests
                
                // .requestMatchers("myAccount").hasAuthority("VIEWACCOUNT")           
                // .requestMatchers("myBalance").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
                // .requestMatchers("myCards").hasAuthority("VIEWCARDS")
                // .requestMatchers("myLoans").hasAuthority("VIEWLOANS")
                
                // In case of roles we dont need to speify "ROLE_" in front of role name in code, Spring will automatically do it.
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
