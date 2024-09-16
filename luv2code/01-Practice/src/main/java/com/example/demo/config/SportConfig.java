package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Coach;
import com.example.demo.model.SwimCoach;

@Configuration
public class SportConfig {
    @Bean
    public Coach swimCoach()
    {
        return new SwimCoach();
    }
}
