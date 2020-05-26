package com.fabianbg.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.fabianbg.infra.repo")
@EntityScan("com.fabianbg.domain.model")
public class ApplicationConfig {

}