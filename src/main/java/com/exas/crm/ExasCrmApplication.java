package com.exas.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration 
public class ExasCrmApplication {

	public static void main(String[] args) {
        System.out.println("Om Vigneshwaraaya Namahaa");
		SpringApplication.run(ExasCrmApplication.class, args);
	}
	
}
