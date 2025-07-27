// src/main/java/com/luciano/sistemabancario/model/ContaPoupanca.java
package com.luciano.sistemabancario.model;

import com.luciano.sistemabancario.util.MoedaUtil;

// @NoArgsConstructor(callSuper = true)
// @ToString(callSuper = true)
public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança (Número: " + getNumero() + ") ===");
        imprimirDetalhesExtrato(); // Chama o método protegido da classe base
    }

    /**
     * Aplica um rendimento mensal à conta poupança.
     * @param taxa A taxa de rendimento (ex: 0.01 para 1%).
     */
    public void aplicarRendimentoMensal(double taxa) {
        if (taxa > 0) {
            double rendimento = saldo * taxa;
            saldo += rendimento;
            historic.add("Rendimento mensal aplicado: +" + MoedaUtil.formatar(rendimento));
            System.out.println("Rendimento de " + MoedaUtil.formatar(rendimento) + " aplicado na Conta Poupança " + getNumero());
        } else {
            System.out.println("Taxa de rendimento inválida: " + (taxa * 100) + "%");
        }
    }
}
