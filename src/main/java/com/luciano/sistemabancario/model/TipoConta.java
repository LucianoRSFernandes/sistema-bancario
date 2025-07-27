package com.luciano.sistemabancario.model;

import java.util.Arrays;

public enum TipoConta {
    CONTA_CORRENTE(1, "Conta Corrente"),
    CONTA_POUPANCA(2, "Conta Poupança");

    private final int codigo;
    private final String descricao;

    TipoConta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter o enum a partir de um código
    public static TipoConta fromCodigo(int codigo) {
        return Arrays.stream(TipoConta.values())
                .filter(tc -> tc.getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código de tipo de conta inválido: " + codigo));
    }
}

