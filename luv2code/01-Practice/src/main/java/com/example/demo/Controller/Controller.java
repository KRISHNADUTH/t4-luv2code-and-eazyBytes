package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;

@RestController
public class Controller {

    private UserService service;

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/")
    public Object home() {
        return "Hello world, Good Morning all.........";
    }

    @GetMapping("/teaminfo")
    public String teamInfo() {
        return "Teams - " + teamName + " and Coach is - " + coachName;
    }
    

    public Controller(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    public Object registerUser(@RequestBody UserDto userDto)
    {
        System.out.println(userDto.toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return service.registerUser(userDto);
    }
}
