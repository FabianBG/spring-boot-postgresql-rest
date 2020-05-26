package com.fabianbg.app.transport.http;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fabianbg.app.Mapper;
import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;
import com.fabianbg.domain.service.IDeveloperService;
import com.fabianbg.fixtures.DeveloperFixtures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest extends Mapper {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDeveloperService service;

    @Test
    public void getDevelopers_shouldRetunAcorrectResponse() throws Exception {
        List<Developer> list = DeveloperFixtures.newListDevelopers();
        String response = this.mapToJson(list);

        when(service.getAll()).thenReturn(list);

        this.mockMvc.perform(get("/developers")).andExpect(status().isOk()).andExpect(content().json(response));
    }

    @Test
    public void postDevelopers_shouldRetunAcorrectResponse() throws Exception {
        Developer data = DeveloperFixtures.newDeveloper();
        String json = this.mapToJson(data);

        when(service.create(data)).thenReturn(data);

        this.mockMvc.perform(post("/developers").contentType("application/json").content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void putDevelopers_shouldRetunAcorrectResponse() throws Exception {
        Developer data = DeveloperFixtures.newDeveloperUpdated();
        DeveloperUpdateDTO update = DeveloperFixtures.newDeveloperUpdate();
        String json = this.mapToJson(update);

        when(service.create(data)).thenReturn(data);

        this.mockMvc.perform(put("/developers/" + data.getId()).contentType("application/json").content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteDevelopers_shouldRetunAcorrectResponse() throws Exception {
        Developer data = DeveloperFixtures.newDeveloper();

        when(service.delete(data.getId())).thenReturn(data.getId());

        this.mockMvc.perform(delete("/developers/" + data.getId())).andExpect(status().isOk())
                .andExpect(content().string(data.getId()));
    }
}
