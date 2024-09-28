package model;

public class Administrador extends Usuario {
    private String senha;

    public Administrador(String nome, String email, String senha, String cpf, String endereco, Telefone telefone ){
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