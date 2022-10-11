package com.example.testingjava.aula1.aulaAoVivo.aula03.dto;

import com.example.testingjava.aula1.aulaAoVivo.aula03.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfDTO {
    private Conta origem;
    private Conta destino;
}