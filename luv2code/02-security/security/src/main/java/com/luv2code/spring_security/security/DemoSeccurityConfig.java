package com.luv2code.spring_security.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSeccurityConfig {
    /*   User details stored within the program itself
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails john = User.builder().username("john").password("{noop}0000").roles("EMPLOYEE").build();
        UserDetails clayton = User.builder().username("clayton").password("{noop}1111").roles("EMPLOYEE", "MANAGER").build();
        UserDetails dileep = User.builder().username("dileep").password("{noop}2222").roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, clayton, dileep);
    }
    */

    /*  User details stored inside DB within tables having correct name.
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }
    */

    // User details stored inside DB within tables having different name.
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource)
    {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT name,pwd,ok FROM members WHERE name=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT name,role FROM roles WHERE name=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->
        configurer.requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
        .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
        .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
        .requestMatchers(HttpMethod.PUT, "api/employees/**").hasRole("MANAGER")
        .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}