package com.cmpt.focusdriving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class FocusdrivingApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_PASS", dotenv.get("DB_PASS"));
        System.setProperty("DEFAULT_PASS", dotenv.get("DEFAULT_PASS"));
		SpringApplication.run(FocusdrivingApplication.class, args);
	}

}
