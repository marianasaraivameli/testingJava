package com.example.testingjava.aula1.aulaAoVivo.aula03.util;

public class NumberGenerator {
    private int next;
    private final static NumberGenerator instancia = new NumberGenerator();

    private NumberGenerator() {

    }

    public static NumberGenerator getInstance() {
        return instancia;
    }

    public int getNext() {
        return ++next;
    }
}

