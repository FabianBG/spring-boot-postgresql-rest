package com.fabianbg.infra.repo;

import com.fabianbg.domain.model.Developer;

import org.springframework.data.repository.CrudRepository;

public interface IDeveloperPostgresRepo extends CrudRepository<Developer, String> {
}
