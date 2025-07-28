package com.luciano.sistemabancario.model;

import com.luciano.sistemabancario.util.MoedaUtil;

public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO = 0.005;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente (Número: " + getNumero() + ") ===");
        imprimirDetalhesExtrato();
        
    }

    public void aplicarTaxaManutencao() {
        double taxaValor = saldo * TAXA_MANUTENCAO;
        if (sacar(taxaValor)) {
            historic.add("Taxa de manutenção aplicada: -" + MoedaUtil.formatar(taxaValor));
            System.out.println("Taxa de manutenção de " + MoedaUtil.formatar(taxaValor) + " aplicada na Conta Corrente " + getNumero());
        } else {
            System.out.println("Não foi possível aplicar taxa de manutenção na Conta Corrente " + getNumero() + ": Saldo insuficiente.");
        }
    }
}
