package com.example.testingjava.aula1.praticaIntegradora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String studentName;
    private List<Subject> subjects;
}
