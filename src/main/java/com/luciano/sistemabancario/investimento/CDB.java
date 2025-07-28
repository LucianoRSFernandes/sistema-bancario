package com.luciano.sistemabancario.investimento;

import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.ToString; 

public class CDB extends Investimento {

public CDB(double valorAplicado, LocalDate dataAplicacao, double taxaRendimentoDiaria) {
        super(valorAplicado, dataAplicacao, taxaRendimentoDiaria);
    }

    @Override
    public double calcularRendimento(long dias) {
        return valorAplicado * taxaRendimentoDiaria * dias;
    }

    @Override
    public String getTipo() {
        return "CDB";
    }
}
