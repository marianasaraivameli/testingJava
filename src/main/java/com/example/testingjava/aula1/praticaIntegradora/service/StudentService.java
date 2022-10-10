package com.example.testingjava.aula1.praticaIntegradora.service;

import com.example.testingjava.aula1.praticaIntegradora.model.Student;
import com.example.testingjava.aula1.praticaIntegradora.model.Subject;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<Student> createNewStudent(Student student) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        studentsList = getAll();

        studentsList = new ArrayList<>(studentsList);
        studentsList.add(student);

        try {
            writer.writeValue(new File(LINK_FILE), studentsList);
        } catch (Exception ex) {
            System.out.println("Error create new user");
        }
        return studentsList;
    }

}
