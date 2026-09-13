package com.example.APIExamenTecnico;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiExamenTecnicoApplication implements CommandLineRunner {

	@Value("${spring.data.mongodb.uri:NO_LEIDO}")
	private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(ApiExamenTecnicoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(">>> URI LEIDA: " + mongoUri);
	}
}
