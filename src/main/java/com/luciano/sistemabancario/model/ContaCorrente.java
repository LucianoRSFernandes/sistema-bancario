// src/main/java/com/luciano/sistemabancario/model/ContaCorrente.java
package com.luciano.sistemabancario.model;

import com.luciano.sistemabancario.util.MoedaUtil;

// @NoArgsConstructor(callSuper = true) // Chama o construtor padrão da superclasse
// @ToString(callSuper = true) // Inclui o toString da superclasse
public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO = 0.005; // 0.5% sobre o saldo ou valor fixo

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente (Número: " + getNumero() + ") ===");
        imprimirDetalhesExtrato(); // Chama o método protegido da classe base
        // Opcional: imprimir taxa de manutenção específica
    }

    /**
     * Aplica uma taxa de manutenção na conta corrente.
     */
    public void aplicarTaxaManutencao() {
        double taxaValor = saldo * TAXA_MANUTENCAO; // Exemplo: taxa percentual
        if (sacar(taxaValor)) { // Tenta sacar o valor da taxa
            historic.add("Taxa de manutenção aplicada: -" + MoedaUtil.formatar(taxaValor));
            System.out.println("Taxa de manutenção de " + MoedaUtil.formatar(taxaValor) + " aplicada na Conta Corrente " + getNumero());
        } else {
            System.out.println("Não foi possível aplicar taxa de manutenção na Conta Corrente " + getNumero() + ": Saldo insuficiente.");
        }
    }
}