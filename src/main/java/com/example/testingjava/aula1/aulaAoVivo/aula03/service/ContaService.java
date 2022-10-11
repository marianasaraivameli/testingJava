package com.example.testingjava.aula1.aulaAoVivo.aula03.service;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.dto.TransfDTO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.ContaInexistenteException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaDAO dao;

    public TransfDTO transferir(int numeroContaOrigem, int numeroContaDestino, double valor) throws ContaInexistenteException, InvalidNumberException {
        Conta contaOrigem = dao.getConta(numeroContaOrigem);
        Conta contaDestino = dao.getConta(numeroContaDestino);

        boolean saqueRealizado = contaOrigem.sacar(valor);

        if(saqueRealizado) {
            contaDestino.depositar(valor);
        }

        dao.updateConta(contaOrigem);
        dao.updateConta(contaDestino);

        return new TransfDTO(contaOrigem, contaDestino);
    }
}