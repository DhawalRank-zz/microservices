package com.userui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@SpringBootApplication
public class UseruiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseruiApplication.class, args);
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }
}
