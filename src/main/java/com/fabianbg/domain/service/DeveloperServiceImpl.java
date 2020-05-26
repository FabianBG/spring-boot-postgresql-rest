package com.fabianbg.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;
import com.fabianbg.infra.repo.IDeveloperPostgresRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl implements IDeveloperService {

    @Autowired
    private IDeveloperPostgresRepo developerRepo;

    @Override
    public Developer create(Developer data) {
        data.setId(java.util.UUID.randomUUID().toString());
        return this.developerRepo.save(data);
    }

    @Override
    public List<Developer> getAll() {
        final Iterable<Developer> iterator = this.developerRepo.findAll();
        return StreamSupport.stream(iterator.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public String delete(String id) {
        this.developerRepo.deleteById(id);
        return id;
    }

    @Override
    public Developer update(String id, DeveloperUpdateDTO updateData) {
        Developer data = this.developerRepo.findById(id).get();
        data.setNames(updateData.getNames());
        data.setGithubLink(updateData.getGithubLink());
        data.setTechStack(updateData.getTechStack());

        return this.developerRepo.save(data);
    }
}