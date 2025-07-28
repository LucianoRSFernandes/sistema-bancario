package com.luciano.sistemabancario.investimento;

import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.ToString; 

public class LCI extends Investimento {
    
    public LCI(double valorAplicado, LocalDate dataAplicacao, double taxaRendimentoDiaria) {
        super(valorAplicado, dataAplicacao, taxaRendimentoDiaria);
    }

    @Override
    public double calcularRendimento(long dias) {
        return valorAplicado * taxaRendimentoDiaria * dias;
    }

    @Override
    public String getTipo() {
        return "LCI";
    }
}
