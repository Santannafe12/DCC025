// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

public class Administrador extends Usuario {
    private String senha;

    public Administrador(String nome, String email, String senha, String cpf, String endereco, String telefone ){
        super(nome, email, cpf, endereco, telefone, Tipo.ADMIN);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}