package com.example.testingjava.aula1.praticaIntegradora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @NotBlank
    @NotEmpty
    private String studentName;

    @NotEmpty
    private List<Subject> subjects;
}
