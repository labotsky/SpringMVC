package com.simple.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.simple.bean")
public class WebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

}
