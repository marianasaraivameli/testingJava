package com.example.testingjava.aula1.aulaAoVivo.testes02.controller;

import com.example.testingjava.aula1.aulaAoVivo.testes02.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity<UserDTO> newUser(@Valid @RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userDto);
    }
}
