package com.example.testingjava.aula1.aulaAoVivo.testes02.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório!")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Min(value = 18, message = "A idade mínima é 18 anos!")
    private int idade;
}
