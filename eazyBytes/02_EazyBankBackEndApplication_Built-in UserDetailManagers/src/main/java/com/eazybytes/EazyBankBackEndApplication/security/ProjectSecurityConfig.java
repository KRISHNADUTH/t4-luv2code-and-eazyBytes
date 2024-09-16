package com.eazybytes.EazyBankBackEndApplication.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    // User details - 
    /* 
@Bean
public InMemoryUserDetailsManager userDetailsManager()
{
    UserDetails admin = User.builder().username("admin")
            .password("{bcrypt}$2a$12$3c2lz9VMw38TQFeZPl9MnOmzyPqipeDR2xSr2ZGIS1osLSXiR6Hoe").authorities("admin")
            .build();
    UserDetails user = User.builder().username("user").password("{noop}1111").authorities("user").build();
    return new InMemoryUserDetailsManager(admin, user);
}

@Bean
public JdbcUserDetailsManager userDetailsManager(DataSource dataSource)
{
    return new JdbcUserDetailsManager(dataSource);
}
*/

@Bean
public JdbcUserDetailsManager userDetailsManager(DataSource dataSource)
{
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, pwd, 1 AS enabled FROM customer WHERE email=?");
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM customer where email=?");
    return jdbcUserDetailsManager;
}

    // Cutom security configuration.
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
{
    http.authorizeHttpRequests(requests -> requests
            .requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated()
            .requestMatchers("/contact", "/notices", "/register").permitAll());
    http.httpBasic(Customizer.withDefaults());
    http.formLogin(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());
    return http.build();
}



// storing pass word in BCrypt format inside DB
@Bean
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}


}
