package com.example.testingjava.aula1.aulaAoVivo.aula03.model;

import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContaCorrenteTest {

    private ContaCorrente contaCorrente;

    @BeforeEach
    void setup() {
        contaCorrente = new ContaCorrente(1, "Cliente 1");
    }

    @Test
    void sacar_retorneTrue_quantoHaSaldoSuficiente() throws InvalidNumberException {
        double valorDeposito = 100;
        double valorSaque = 50;
        contaCorrente.depositar(valorDeposito);

        boolean sucesso = contaCorrente.sacar(valorSaque);

        assertThat(contaCorrente.getSaldo()).isEqualTo(valorDeposito-valorSaque);
        assertThat(sucesso).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"100,50", "50,50", "10,10"})
    void sacar2_retorneTrue_quantoHaSaldoSuficiente(double valorDeposito, double valorSaque) throws InvalidNumberException {
        contaCorrente.depositar(valorDeposito);

        boolean sucesso = contaCorrente.sacar(valorSaque);

        assertThat(contaCorrente.getSaldo()).isEqualTo(valorDeposito-valorSaque);
        assertThat(sucesso).isTrue();
    }

    @Test
    void sacar_returnoFalse_quandoSaldoInsuficiente() throws InvalidNumberException {
        double valorSaque = 100;
        boolean sucesso = contaCorrente.sacar(valorSaque);

        assertThat(contaCorrente.getSaldo()).isZero();
        assertThat(sucesso).isFalse();
    }

    @Test
    void sacar_lancarInvalidNumberException_quandoValorInvalido() {
        double valorSaque = -100;

        assertThatThrownBy(() -> contaCorrente.sacar(valorSaque))
                .isInstanceOf(InvalidNumberException.class);
    }

//    @Test
//    void toString_test() {
//        contaCorrente.toString();
//        assertThat("True").isTrue();
//    }
}
