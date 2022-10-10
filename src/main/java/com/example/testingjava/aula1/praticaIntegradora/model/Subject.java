package com.example.testingjava.aula1.praticaIntegradora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private Double score;
}
