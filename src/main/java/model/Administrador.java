package model;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String cpf, String endereco, Telefone telefone ){
        super(nome, email, cpf, endereco, telefone, Tipo.ADMIN);
        this.senha = senha;
    }

    public double getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}