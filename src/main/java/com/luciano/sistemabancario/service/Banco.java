package com.luciano.sistemabancario.service;

import com.luciano.sistemabancario.model.Cliente;
import com.luciano.sistemabancario.model.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {
    private List<Cliente> clientes;
    private List<Conta> contas;
    private String nomeBanco;

    public Banco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
        System.out.println("Banco " + nomeBanco + " inicializado.");
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
    
    public void adicionarCliente(Cliente cliente) {
        if (cliente != null && buscarClientePorCpf(cliente.getCpf()).isEmpty()) {
            this.clientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " adicionado ao banco.");
        } else {
            System.out.println("Erro: Cliente com CPF " + cliente.getCpf() + " já existe ou é inválido.");
        }
    }

    public void adicionarConta(Conta conta) {
        if (conta != null && buscarContaPorNumero(conta.getNumero()).isEmpty()) {
            this.contas.add(conta);
            System.out.println("Conta " + conta.getNumero() + " do cliente " + conta.getCliente().getNome() + " adicionada ao banco.");
        } else {
            System.out.println("Erro: Conta " + conta.getNumero() + " já existe ou é inválida.");
        }
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public Optional<Conta> buscarContaPorNumero(int numero) {
        return contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst();
    }
    
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Conta> listarContas() {
        return new ArrayList<>(contas);
    }

}
