package com.example.demo.model;

public class SwimCoach implements Coach {
    
    public SwimCoach()
    {
        System.out.println("Swim coach object created");
    }

    @Override
    public String getDailyWorkOut() {
        return "Swim for 100 miles";
    }
    
}
