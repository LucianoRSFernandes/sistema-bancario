package com.luciano.sistemabancario.model;

import com.luciano.sistemabancario.investimento.Investimento;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor 
@ToString 
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private List<Investimento> investimentos;
    
    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.investimentos = new ArrayList<>();
    }
    
    public void adicionarInvestimento(Investimento investimento) {
        if (this.investimentos == null) {
            this.investimentos = new ArrayList<>(); 
        }
        this.investimentos.add(investimento);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
