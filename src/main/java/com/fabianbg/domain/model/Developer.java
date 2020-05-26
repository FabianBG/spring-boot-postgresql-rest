package com.fabianbg.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Developer {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nombres_completos")
    private String names;

    @JsonProperty("link_github")
    private String githubLink;

    @JsonProperty("tecnologias_conocidas")
    private String[] techStack;
}