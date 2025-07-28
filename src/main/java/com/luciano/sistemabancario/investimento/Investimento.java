package com.luciano.sistemabancario.investimento;

import com.luciano.sistemabancario.util.MoedaUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Investimento {
    protected double valorAplicado;
    protected LocalDate dataAplicacao;
    protected double taxaRendimentoDiaria;

    public abstract double calcularRendimento(long dias);

    public void imprimirDetalhes() {
        System.out.println("Tipo de Investimento: " + getTipo());
        System.out.println("  Valor Aplicado: " + MoedaUtil.formatar(valorAplicado));
        System.out.println("  Data de Aplicação: " + dataAplicacao);
        System.out.println("  Taxa de Rendimento Diária: " + (taxaRendimentoDiaria * 100) + "%");
    }

    public abstract String getTipo();
}
