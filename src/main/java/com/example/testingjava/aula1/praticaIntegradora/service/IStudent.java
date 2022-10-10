package com.example.testingjava.aula1.praticaIntegradora.service;

import com.example.testingjava.aula1.praticaIntegradora.model.Student;

import java.util.List;

public interface IStudent {
    public List<Student> getAll();
    public List<Student> createNewStudent(Student student);
}
