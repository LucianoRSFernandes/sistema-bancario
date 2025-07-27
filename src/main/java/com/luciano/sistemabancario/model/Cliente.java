// src/main/java/com/luciano/sistemabancario/model/Cliente.java
package com.luciano.sistemabancario.model;

import com.luciano.sistemabancario.investimento.Investimento;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects; // Importe Objects para equals/hashCode manual se precisar de controle extra

// @Data é um atalho para @Getter, @Setter, @ToString, @EqualsAndHashCode, @NoArgsConstructor
// Para maior controle, podemos usar anotações individuais.
@Getter
@Setter
@NoArgsConstructor // Construtor sem argumentos (útil para frameworks)
@ToString // Gera o método toString()
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private List<Investimento> investimentos;

    // Construtor manual, pois precisamos inicializar a lista de investimentos
    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.investimentos = new ArrayList<>();
    }

    // Método adicionarInvestimento() é mantido, pois não é boilerplate
    public void adicionarInvestimento(Investimento investimento) {
        if (this.investimentos == null) {
            this.investimentos = new ArrayList<>(); // Garante que a lista não seja nula se @NoArgsConstructor for usado sem inicialização
        }
        this.investimentos.add(investimento);
    }

    // Para equals e hashCode, o Lombok gera automaticamente com @EqualsAndHashCode.
    // Se precisar de controle específico (ex: apenas CPF para igualdade), use @EqualsAndHashCode(of = {"cpf"})
    // Por enquanto, o padrão gerado por @Data (se usado) ou se você adicionar @EqualsAndHashCode sozinho já é bom.
    // Ou mantenha manual se o controle for muito específico:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf); // Clientes são iguais se tiverem o mesmo CPF
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}