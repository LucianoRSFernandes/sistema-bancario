// src/main/java/com/luciano/sistemabancario/model/Conta.java
package com.luciano.sistemabancario.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.luciano.sistemabancario.util.MoedaUtil;
import lombok.Getter;
import lombok.Setter; // Adicionado Setter para saldo, se necessário fora dos métodos de operação
import lombok.ToString; // Opcional, mas útil para debug

@Getter // Gera getters para todos os campos
@Setter // Gera setters para todos os campos (saldo é manipulado internamente, mas pode ser útil)
@ToString(of = {"numero", "saldo", "cliente"}) // Gera toString() com campos específicos
public abstract class Conta {
    private static int SEQUENCIAL = 1000;

    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<String> historic;

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.historic = new ArrayList<>();
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historic.add("Depósito de " + MoedaUtil.formatar(valor) + " em " + LocalDateTime.now());
            System.out.println("Depósito de " + MoedaUtil.formatar(valor) + " realizado na conta " + numero);
        } else {
            System.out.println("Valor de depósito inválido: " + MoedaUtil.formatar(valor));
        }
    }

    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido: " + MoedaUtil.formatar(valor));
            return false;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque de " + MoedaUtil.formatar(valor) + ". Saldo atual: " + MoedaUtil.formatar(saldo));
            return false;
        }
        saldo -= valor;
        historic.add("Saque de " + MoedaUtil.formatar(valor) + " em " + LocalDateTime.now());
        System.out.println("Saque de " + MoedaUtil.formatar(valor) + " realizado na conta " + numero);
        return true;
    }

    public void transferir(double valor, Conta destino) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            historic.add("Transferência de " + MoedaUtil.formatar(valor) + " para conta " + destino.getNumero() + " em " + LocalDateTime.now());
            System.out.println("Transferência de " + MoedaUtil.formatar(valor) + " da conta " + numero + " para conta " + destino.getNumero() + " realizada com sucesso.");
            return;
        }
        System.out.println("Falha na transferência de " + MoedaUtil.formatar(valor) + " da conta " + numero + " para conta " + destino.getNumero() + ".");
    }

    public void transferirPix(double valor, Conta destino) {
        String chavePix = "";
        if (this.sacar(valor)) {
            destino.depositar(valor);
            historic.add("PIX de " + MoedaUtil.formatar(valor) + " para chave " + chavePix + " (conta " + destino.getNumero() + ") em " + LocalDateTime.now());
            System.out.println("PIX de " + MoedaUtil.formatar(valor) + " da conta " + numero + " para chave " + chavePix + " (conta " + destino.getNumero() + ") realizada com sucesso.");
            return;
        }
        System.out.println("Falha no PIX de " + MoedaUtil.formatar(valor) + " da conta " + numero + " para chave " + chavePix + ".");
    }

    public abstract void imprimirExtrato();

    protected void imprimirDetalhesExtrato() {
        System.out.println("Número da Conta: " + numero);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Histórico de Transações:");
        if (historic.isEmpty()) {
            System.out.println("  Nenhuma transação registrada.");
        } else {
            for (String linha : historic) {
                System.out.println("  - " + linha);
            }
        }
        System.out.println("Saldo atual: " + MoedaUtil.formatar(saldo));
    }
}
