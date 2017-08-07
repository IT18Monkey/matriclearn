package com.whh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.whh")
public class MatricLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatricLearnApplication.class, args);
	}
}
