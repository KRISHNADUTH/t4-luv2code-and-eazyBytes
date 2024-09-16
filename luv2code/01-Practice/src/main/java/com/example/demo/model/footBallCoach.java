package com.example.demo.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class footBallCoach implements Coach {

    public footBallCoach()
    {
        System.out.println("Football coach created");
    }

    @Override
    public String getDailyWorkOut() {
        return "Sprint for 30 mins daily......";
    }

}