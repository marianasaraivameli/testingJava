package com.example.testingjava.aula1.aulaAoVivo.aula03.service;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.dto.TransfDTO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.ContaInexistenteException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.ContaCorrente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService service;

    @Mock
    private ContaDAO dao;

    private ContaCorrente contaOrigem;
    private ContaCorrente contaDestino;


    @Test
    @DisplayName("Verifica se a transferência foi realizada com sucesso!")
    void transferir_retornoTransf_quandoRealizadaComSucesso() throws ContaInexistenteException, InvalidNumberException {
        contaOrigem = new ContaCorrente(10, "Mariana");
        contaDestino = new ContaCorrente(20, "Maria Olivia");
        double valorSaldo = 500;
        double valorOperacao = 50;

        Mockito.when(dao.getConta(ArgumentMatchers.anyInt()))
                .thenReturn(contaOrigem);
        Mockito.when(dao.updateConta(ArgumentMatchers.any()))
                .thenReturn(true);

        Mockito.when(dao.getConta(ArgumentMatchers.anyInt()))
                .thenReturn(contaDestino);
        Mockito.when(dao.updateConta(ArgumentMatchers.any()))
                .thenReturn(true);

        contaOrigem.depositar(valorSaldo);
        contaOrigem.sacar(valorOperacao);
        contaDestino.depositar(valorOperacao);

        TransfDTO trans = service.transferir(contaOrigem.getNumero(), contaDestino.getNumero(), valorOperacao);

        double ccOrigem = contaOrigem.getSaldo();
        double ccDestino = contaDestino.getSaldo();

        assertThat(trans).isNotNull();
        assertThat(ccOrigem).isEqualTo(contaOrigem.getSaldo());
        assertThat(ccDestino).isEqualTo(valorOperacao);
    }


    @Test
    @DisplayName("Verifica se a conta não existe,  lança ContaInexistenteException!")
    void transgerir_lancaContaInexistente_quandoContaNaoExiste() throws ContaInexistenteException {
        int numeroContaInexistente = 999;
        double valorOperacao = 100;

        BDDMockito.given(dao.getConta(ArgumentMatchers.anyInt()))
                .willThrow(new ContaInexistenteException("Conta inexistente"));

        Assertions.assertThrows(ContaInexistenteException.class, () -> {
            service.transferir(numeroContaInexistente, 2, valorOperacao);
        });

        verify(dao, never()).updateConta(ArgumentMatchers.any());
    }

}
