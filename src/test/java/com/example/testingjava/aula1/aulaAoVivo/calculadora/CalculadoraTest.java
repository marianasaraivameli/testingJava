package com.example.testingjava.aula1.aulaAoVivo.calculadora;

import com.example.testingjava.aula1.aulaAoVivo.calculadora.Calculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    // sempre que for fazer um teste precisamos ficar atentos aos pontos:
    // dado que: o que irei testar? o que espero retorno
    // quando:
    // então:

    Calculadora calculadora;

    @BeforeEach
    public void setup() {
        calculadora = new Calculadora();
    }

    @Test
    @DisplayName("Valida a soma")
    public void soma_returnDouble_whenValidInput() {
        // 3 estapas:
        // setup:
        double n1 = 10;
        double n2 = 20;
        double expected = 30;

        // run(executar):
        double response = calculadora.soma(n1, n2);

        // validate(validar):
        assertEquals(expected, response);


    }

    @Test
    @DisplayName("Valida a divisão")
    public void dividir_returnDouble_whenTwoPositiveNumbers() {
        double n1 = 20;
        double n2 = 5;
        double expected = 4;

        double response = calculadora.dividir(n1, n2);

        assertEquals(expected, response);

    }

    @Test
    @DisplayName("Verifica se n2 é igual a zero")
    public void dividir_returnDouble_whenDivisorEqualsZero() {
        double n1 = 20;
        double n2 = 0;
        double expected = 0;

        double response = calculadora.dividir(n1, n2);

        assertEquals(expected, response);

    }
}
