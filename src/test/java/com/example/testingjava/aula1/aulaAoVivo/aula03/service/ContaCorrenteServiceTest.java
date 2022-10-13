package com.example.testingjava.aula1.aulaAoVivo.aula03.service;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.ContaInexistenteException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ContaCorrenteServiceTest {

    @InjectMocks
    private ContaCorrenteService service;

    @Mock
    private ContaDAO dao;

    private ContaCorrente contaCorrente;

    @BeforeEach
    void setup() {
        contaCorrente = new ContaCorrente(1, "Cliente 1");
    }

    @Test
    @DisplayName("Valida nova Conta")
    void novaContaCorrente_retorneNovaContaCorrente_quandoSucesso() {
        Mockito.when(dao.novaContaCorrente(ArgumentMatchers.anyString()))
                .thenReturn(contaCorrente);

        ContaCorrente cc = service.novaContaCorrente(contaCorrente.getCliente());

        assertThat(cc).isNotNull();
        assertThat(cc.getNumero()).isPositive();
        assertThat(cc.getCliente()).isEqualTo(contaCorrente.getCliente());
    }

    @Test
    void getConta_retornaContaCorrente_quandoContaExiste() throws ContaInexistenteException {
        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .thenReturn(contaCorrente);

        ContaCorrente contaCorrenteFound = service.getConta(contaCorrente.getNumero());

        assertThat(contaCorrenteFound).isNotNull();
        assertThat(contaCorrenteFound.getNumero()).isEqualTo(contaCorrente.getNumero());
        assertThat(contaCorrenteFound.getSaldo()).isZero();
    }

    @Test
    void getConta_lancaContaInexistenteException_quantoContaNaoExiste() throws ContaInexistenteException {
        int numeroContaInexistente = 999;

        BDDMockito.given(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .willThrow(new ContaInexistenteException("Conta não existe"));

        assertThrows(ContaInexistenteException.class, () -> {
            service.getConta(numeroContaInexistente);
        });
    }

    @Test
    void sacar_returnTrue_quandoContaExisteEValorValidoESaldoSuficiente() throws InvalidNumberException, ContaInexistenteException {
        final double valorOperacao = 100;

        contaCorrente.depositar(valorOperacao);

        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .thenReturn(contaCorrente);
        Mockito.when(dao.updateConta(ArgumentMatchers.any()))
                .thenReturn(true);

        boolean sucesso = service.sacar(contaCorrente.getNumero(), valorOperacao);

        assertThat(sucesso).isTrue();
        assertThat(contaCorrente.getSaldo()).isZero();
    }

    @Test
    void sacar_lancaContaInexistenteException_quandoContaNaoExiste() throws ContaInexistenteException {
        int numeroContaInexistente = 999;
        double valorOpercao = 100;

        BDDMockito.given(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .willThrow(new ContaInexistenteException("Conta não existe"));

        assertThrows(ContaInexistenteException.class, () -> {
            service.sacar(numeroContaInexistente, valorOpercao);
        });

        verify(dao, never()).updateConta(ArgumentMatchers.any());
    }

    @Test
    void sacar_lancarInvalidNumberException_quandoValorOperacaoInvalido() throws ContaInexistenteException {
        final double valorOperacao = -100;

        ContaCorrente novaConta = new ContaCorrente(contaCorrente.getNumero(), contaCorrente.getCliente());

        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .thenReturn(novaConta);

        assertThrows(InvalidNumberException.class, () -> {
            service.sacar(contaCorrente.getNumero(), valorOperacao);
        });

        verify(dao, never()).updateConta(ArgumentMatchers.any());
    }

    @Test
    void depositar() throws ContaInexistenteException, InvalidNumberException {
        final int valorOperacao = 100;

        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt()))
                .thenReturn(contaCorrente);
        Mockito.when(dao.updateConta(ArgumentMatchers.any()))
                .thenReturn(true);

        service.depositar(contaCorrente.getNumero(), valorOperacao);

        assertThat(contaCorrente.getSaldo()).isEqualTo(valorOperacao);
    }
}