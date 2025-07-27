// src/main/java/com/luciano/sistemabancario/investimento/Investimento.java
package com.luciano.sistemabancario.investimento;

import com.luciano.sistemabancario.util.MoedaUtil;
import lombok.Getter; // Gera getters para os campos
import lombok.NoArgsConstructor; // Necessário para @AllArgsConstructor se for usado
import lombok.AllArgsConstructor; // Gera construtor com todos os campos
import lombok.ToString; // Gera toString()

import java.time.LocalDate;

@Getter // Gera getters para todos os campos
@NoArgsConstructor // Construtor sem argumentos (útil para frameworks)
@AllArgsConstructor // Construtor com todos os argumentos
@ToString // Gera o método toString()
public abstract class Investimento {
    protected double valorAplicado;
    protected LocalDate dataAplicacao;
    protected double taxaRendimentoDiaria; // Taxa diária

    // O construtor manual foi removido, pois @AllArgsConstructor o gera

    /**
     * Calcula o rendimento do investimento.
     * Este método deve ser implementado pelas subclasses.
     * @param dias O número de dias para calcular o rendimento.
     * @return O valor do rendimento.
     */
    public abstract double calcularRendimento(long dias);

    /**
     * Imprime os detalhes básicos do investimento.
     */
    public void imprimirDetalhes() {
        System.out.println("Tipo de Investimento: " + getTipo());
        System.out.println("  Valor Aplicado: " + MoedaUtil.formatar(valorAplicado));
        System.out.println("  Data de Aplicação: " + dataAplicacao);
        System.out.println("  Taxa de Rendimento Diária: " + (taxaRendimentoDiaria * 100) + "%");
    }

    /**
     * Retorna o tipo de investimento (ex: "CDB", "LCI").
     * Deve ser implementado pelas subclasses.
     * @return Uma string com o tipo de investimento.
     */
    public abstract String getTipo();
}
