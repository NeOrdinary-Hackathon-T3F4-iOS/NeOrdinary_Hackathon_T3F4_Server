package com.t3f4.zerowaste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class ZerowasteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerowasteApplication.class, args);
	}

}
