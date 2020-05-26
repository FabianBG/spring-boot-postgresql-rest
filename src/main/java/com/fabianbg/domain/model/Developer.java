package com.fabianbg.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("nombres_completos")
    private String names;

    @JsonProperty("link_github")
    private String githubLink;

    @JsonProperty("tecnologias_conocidas")
    private String[] techStack;
}