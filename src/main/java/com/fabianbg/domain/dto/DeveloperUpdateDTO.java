package com.fabianbg.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeveloperUpdateDTO {

    @JsonProperty("nombres_completos")
    private String names;

    @JsonProperty("link_github")
    private String githubLink;

    @JsonProperty("tecnologias_conocidas")
    private String[] techStack;
}