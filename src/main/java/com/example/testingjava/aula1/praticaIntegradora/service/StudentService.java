package com.example.testingjava.aula1.praticaIntegradora.service;

import com.example.testingjava.aula1.praticaIntegradora.model.Student;
import com.example.testingjava.aula1.praticaIntegradora.model.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudent {
    private static final String LINK_FILE = "src/main/resources/students.json";

    ObjectMapper mapper = new ObjectMapper();

    List<Student> studentsList;

    public List<Student> getAll() {
        try {
            studentsList = Arrays.asList(mapper.readValue(new File(LINK_FILE), Student[].class));
        } catch (Exception ex) {
            System.out.println("Error get all students");
        }
        return studentsList;
    }

    @Override
    public Double calculateAverage() {
        List<Student> students = getAll();

        return null;
    }
}
