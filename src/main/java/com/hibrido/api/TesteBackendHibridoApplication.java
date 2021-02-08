package com.hibrido.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TesteBackendHibridoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteBackendHibridoApplication.class, args);
	}

}
