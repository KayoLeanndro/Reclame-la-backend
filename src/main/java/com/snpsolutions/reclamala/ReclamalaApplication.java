package com.snpsolutions.reclamala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReclamalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReclamalaApplication.class, args);

		System.out.println("DB_HOST: " + System.getenv("DB_HOST"));
		System.out.println("DB_PORT: " + System.getenv("DB_PORT"));
		System.out.println("DB_NAME: " + System.getenv("DB_NAME"));
		System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
	}

}
