package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class CricketCoach implements Coach {
    public CricketCoach()
    {
        System.out.println("Cricketcoach Created");
    }

    // init method definition
    @PostConstruct
    public void doMyStartUPstuff()
    {
        System.out.println("In doMyStartUPstuff() "+getClass().getSimpleName());
    }

    // destroy method definition
    @PreDestroy
    public void doMyCleanUpStuff()
    {
        System.out.println("In doMyCleanUpStuff() ##################################################"+getClass().getSimpleName());
    } 

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowlign for 15 minutes....";
    }
}
