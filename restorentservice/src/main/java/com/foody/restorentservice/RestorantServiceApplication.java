package com.foody.restorentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class RestorantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestorantServiceApplication.class, args);
		Hooks.onOperatorDebug();
	}

}
