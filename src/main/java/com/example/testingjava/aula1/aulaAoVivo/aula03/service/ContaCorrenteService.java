package com.example.testingjava.aula1.aulaAoVivo.aula03.service;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.ContaInexistenteException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.exception.InvalidNumberException;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.Conta;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {

    @Autowired
    private ContaDAO dao;

    public ContaCorrente novaContaCorrente(String cliente) {
        return dao.novaContaCorrente(cliente);
    }

    public ContaCorrente getConta(int numero) throws ContaInexistenteException {
        return dao.getContaCorrente(numero);
    }

    public boolean sacar(int numeroConta, double valor) throws ContaInexistenteException, InvalidNumberException {
        ContaCorrente conta = dao.getContaCorrente(numeroConta);

        if(conta.sacar(valor)) {
            return dao.updateConta(conta);
        }

        return false;
    }

    public void depositar(int numeroConta, double valor) throws ContaInexistenteException,
            InvalidNumberException {
        Conta conta = dao.getContaCorrente(numeroConta);

        conta.depositar(valor);

        dao.updateConta(conta);
    }
}
