package com.eazybytes.springsecuritybasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.eazybytes.springsecuritybasics.controller")
@SpringBootApplication
public class SpringsecuritybasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritybasicsApplication.class, args);
	}

}
