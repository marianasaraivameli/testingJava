package com.example.testingjava.aula1.praticaIntegradora.dto;

import com.example.testingjava.aula1.praticaIntegradora.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String studentName;
    private String message;
    private Double averageScore;
    private List<Subject> subjects;
}
