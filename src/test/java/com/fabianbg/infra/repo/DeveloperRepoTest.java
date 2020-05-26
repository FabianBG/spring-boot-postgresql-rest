package com.fabianbg.infra.repo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fabianbg.app.Application;
import com.fabianbg.app.ApplicationConfig;
import com.fabianbg.domain.model.Developer;
import com.fabianbg.fixtures.DeveloperFixtures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
@ContextConfiguration(classes = { Application.class, ApplicationConfig.class })
public class DeveloperRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IDeveloperPostgresRepo repo;

    @Test
    public void findAll_whenHasRecords_returnListOfRecords() {

        Developer persisted = DeveloperFixtures.newDeveloper();
        entityManager.persist(persisted);
        entityManager.flush();

        Iterable<Developer> iterator = repo.findAll();
        List<Developer> found = StreamSupport.stream(iterator.spliterator(), false).collect(Collectors.toList());

        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getId()).isEqualTo("001");
    }

    @Test
    public void findAll_whenNoRecords_returnEmptyList() {

        Iterable<Developer> iterator = repo.findAll();
        List<Developer> found = StreamSupport.stream(iterator.spliterator(), false).collect(Collectors.toList());

        assertThat(found.size()).isEqualTo(0);
    }

    @Test
    public void save_whenNewEntity_returnPersistedEntity() {
        Developer persisted = DeveloperFixtures.newDeveloper();
        repo.save(persisted);
        Developer result = entityManager.find(Developer.class, persisted.getId());

        assertThat(result.getId()).isEqualTo(persisted.getId());
    }

    @Test
    public void save_whenExist_returnUpdatedEntity() {
        Developer persisted = DeveloperFixtures.newDeveloper();
        entityManager.persist(persisted);
        entityManager.flush();

        persisted.setNames("UPTADE");
        repo.save(persisted);

        Developer result = entityManager.find(Developer.class, persisted.getId());

        assertThat(result.getNames()).isEqualTo("UPTADE");
    }

    @Test
    public void deleteById_whenExist_returnOk() {
        Developer persisted = DeveloperFixtures.newDeveloper();
        entityManager.persist(persisted);
        entityManager.flush();

        repo.deleteById(persisted.getId());

        Developer result = entityManager.find(Developer.class, persisted.getId());

        assertThat(result).isNull();
    }

    @Test()
    public void deleteById_whenNoRecords_returnNull() {
        Developer persisted = DeveloperFixtures.newDeveloper();
        entityManager.persist(persisted);
        entityManager.flush();
        try {
            repo.deleteById("9999999999");

        } catch (EmptyResultDataAccessException e) {
            assertThat(e).isNotNull();
        }
    }
}