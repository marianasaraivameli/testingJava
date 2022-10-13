package com.example.testingjava.aula1.aulaAoVivo.aula03.service;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.dto.TransfDTO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.ContaInexistenteException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.ContaCorrente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService service;

    private ContaCorrente serviceCC;

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
}
