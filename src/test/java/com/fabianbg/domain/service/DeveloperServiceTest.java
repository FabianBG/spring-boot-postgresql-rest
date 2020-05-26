package com.fabianbg.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.fabianbg.app.Application;
import com.fabianbg.app.ApplicationConfig;
import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;
import com.fabianbg.fixtures.DeveloperFixtures;
import com.fabianbg.infra.repo.IDeveloperPostgresRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest(classes = { Application.class, ApplicationConfig.class })
public class DeveloperServiceTest {

    @Autowired
    private IDeveloperService service;

    @MockBean
    private IDeveloperPostgresRepo repo;

    @Test
    public void create_whenData_shouldReturnCreatedRecordWhithUUID() throws Exception {
        Developer data = DeveloperFixtures.newDeveloper();

        when(repo.save(data)).thenReturn(data);

        Developer result = service.create(data);

        assertThat(result).isNotNull();
    }

    @Test
    public void getAll_whenData_shouldReturnCreatedData() throws Exception {
        Iterable<Developer> data = DeveloperFixtures.newIterableDevelopers();
        when(repo.findAll()).thenReturn(data);

        List<Developer> result = service.getAll();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void update_whenIdExist_shouldReturnUpdatedData() throws Exception {
        Developer updatedRecord = DeveloperFixtures.newDeveloperUpdated();
        DeveloperUpdateDTO updateData = DeveloperFixtures.newDeveloperUpdate();

        when(repo.findById(updatedRecord.getId())).thenReturn(Optional.of(updatedRecord));
        when(repo.save(updatedRecord)).thenReturn(updatedRecord);

        Developer result = service.update(updatedRecord.getId(), updateData);

        assertThat(result.getId()).isEqualTo(updatedRecord.getId());
        assertThat(result.getNames()).isEqualTo(updatedRecord.getNames());
    }

    @Test
    public void update_whenIdNotExist_shouldReturnNoSuchElementException() throws Exception {
        Developer updatedRecord = DeveloperFixtures.newDeveloperUpdated();
        DeveloperUpdateDTO updateData = DeveloperFixtures.newDeveloperUpdate();

        when(repo.findById(updatedRecord.getId())).thenReturn(Optional.ofNullable(null));
        when(repo.save(updatedRecord)).thenReturn(updatedRecord);

        try {
            service.update(updatedRecord.getId(), updateData);

        } catch (NoSuchElementException e) {
            assertThat(e).isNotNull();
        }

    }

    @Test
    public void delete_whenIdNotExist_shouldThrowEmptyResultDataAccessException() throws Exception {
        Developer data = DeveloperFixtures.newDeveloper();
        doThrow(EmptyResultDataAccessException.class).when(repo).deleteById(data.getId());

        try {
            service.delete(data.getId());
        } catch (EmptyResultDataAccessException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void delete_whenIdExist_shouldReturnId() throws Exception {
        Developer data = DeveloperFixtures.newDeveloper();
        doNothing().when(repo).deleteById(data.getId());

        String result = service.delete(data.getId());
        assertThat(result).isNotEmpty();
    }
}