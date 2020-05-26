package com.fabianbg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.fabianbg.domain", "com.fabianbg.infra", "com.fabianbg.app" })
@EnableJpaRepositories("com.fabianbg.infra.repo")
@EntityScan("com.fabianbg.domain.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
