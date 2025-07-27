package com.luciano.sistemabancario.investimento;

import java.util.Arrays;

public enum TipoInvestimento {
    CDB(1, "CDB"),
    LCI(2, "LCI");

    private final int codigo;
    private final String descricao;

    TipoInvestimento(int codigo, String descricao) {
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
    public static TipoInvestimento fromCodigo(int codigo) {
        return Arrays.stream(TipoInvestimento.values())
                .filter(ti -> ti.getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código de tipo de investimento inválido: " + codigo));
    }
}

