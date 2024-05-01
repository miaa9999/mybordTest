package com.example.myboard2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Myboard2Application {

	public static void main(String[] args) {
		SpringApplication.run(Myboard2Application.class, args);
	}

}
