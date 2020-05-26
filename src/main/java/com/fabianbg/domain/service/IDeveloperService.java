package com.fabianbg.domain.service;

import java.util.List;

import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;

public interface IDeveloperService {

    public Developer create(Developer data);

    public List<Developer> getAll();

    public Developer update(String id, DeveloperUpdateDTO updateData);

    public String delete(String id);

}