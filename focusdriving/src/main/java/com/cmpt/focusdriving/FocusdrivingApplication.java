package com.cmpt.focusdriving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = { FocusdrivingApplication.class, Jsr310JpaConverters.class })

@SpringBootApplication
public class FocusdrivingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusdrivingApplication.class, args);
	}

}
