package com.CodeCraft.bwtt02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bwtt02Application {

	public static void main(String[] args) {
		SpringApplication.run(Bwtt02Application.class, args);
		System.out.println("Datasource URL: " + System.getenv("MY_DATASOURCE"));

	}

}
