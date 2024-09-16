package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Coach;

@RestController
public class DemoController {
    private Coach myCoach;

    @Autowired  // optional since we are having only one Contructor here.
    public DemoController(@Qualifier("swimCoach") Coach myCoach) {
        System.out.println("DemoController instance created.");
        this.myCoach = myCoach;
    }
    
    public DemoController() {
    }

    @GetMapping("/getDailyWorkOut")
    public String getDailyWorkOut()
    {
        return myCoach.getDailyWorkOut();
    }

}
