package com.fabianbg.infra.repo;

import com.fabianbg.domain.model.Developer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeveloperPostgresRepo extends CrudRepository<Developer, String> {
}
