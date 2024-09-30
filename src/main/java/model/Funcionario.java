// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

import java.util.Objects;

public class Funcionario extends Usuario {
    private double salario;
    private String senha;

    public Funcionario(String nome, String senha, String email, String cpf, String endereco, String telefone,
            double salario) {
        super(nome, email, cpf, endereco, telefone, Tipo.FUNCIONARIO);
        this.salario = salario;
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Funcionario that = (Funcionario) o;
        return this.getCpf().equals(that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
