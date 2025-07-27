// src/main/java/com/luciano/sistemabancario/investimento/LCI.java
package com.luciano.sistemabancario.investimento;

import java.time.LocalDate;
import lombok.NoArgsConstructor; // Não é estritamente necessário aqui
import lombok.ToString; // Opcional, herdará de Investimento

// @NoArgsConstructor(callSuper = true)
// @ToString(callSuper = true)
public class LCI extends Investimento {

    // O construtor manual é mantido para chamar super(args) explicitamente
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