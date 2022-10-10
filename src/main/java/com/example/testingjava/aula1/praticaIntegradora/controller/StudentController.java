package com.example.testingjava.aula1.praticaIntegradora.controller;

import com.example.testingjava.aula1.praticaIntegradora.model.Student;
import com.example.testingjava.aula1.praticaIntegradora.service.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudent studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
     return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }
}
