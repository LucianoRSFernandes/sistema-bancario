// src/main/java/com/luciano/sistemabancario/service/Banco.java
package com.luciano.sistemabancario.service;

import com.luciano.sistemabancario.model.Cliente;
import com.luciano.sistemabancario.model.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Para retornar valores que podem ou não existir

/**
 * Classe Banco atua como um repositório em memória para Clientes e Contas.
 * Gerencia a adição e busca de entidades no sistema.
 */
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

    /**
     * Adiciona um novo cliente ao banco.
     * @param cliente O objeto Cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        if (cliente != null && buscarClientePorCpf(cliente.getCpf()).isEmpty()) {
            this.clientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " adicionado ao banco.");
        } else {
            System.out.println("Erro: Cliente com CPF " + cliente.getCpf() + " já existe ou é inválido.");
        }
    }

    /**
     * Adiciona uma nova conta ao banco.
     * @param conta O objeto Conta a ser adicionado.
     */
    public void adicionarConta(Conta conta) {
        if (conta != null && buscarContaPorNumero(conta.getNumero()).isEmpty()) {
            this.contas.add(conta);
            System.out.println("Conta " + conta.getNumero() + " do cliente " + conta.getCliente().getNome() + " adicionada ao banco.");
        } else {
            System.out.println("Erro: Conta " + conta.getNumero() + " já existe ou é inválida.");
        }
    }

    /**
     * Busca um cliente pelo CPF.
     * @param cpf O CPF do cliente a ser buscado.
     * @return Um Optional contendo o Cliente, se encontrado, ou Optional.empty() caso contrário.
     */
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    /**
     * Busca uma conta pelo número.
     * @param numero O número da conta a ser buscada.
     * @return Um Optional contendo a Conta, se encontrada, ou Optional.empty() caso contrário.
     */
    public Optional<Conta> buscarContaPorNumero(int numero) {
        return contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst();
    }

    /**
     * Retorna uma lista imutável de todos os clientes no banco.
     * @return Lista de Clientes.
     */
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia para evitar modificações externas diretas
    }

    /**
     * Retorna uma lista imutável de todas as contas no banco.
     * @return Lista de Contas.
     */
    public List<Conta> listarContas() {
        return new ArrayList<>(contas); // Retorna uma cópia para evitar modificações externas diretas
    }

    // Métodos para gestão de clientes e contas (ex: remover, atualizar) poderiam ser adicionados aqui.
}
