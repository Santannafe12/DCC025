package model;

public class Administrador extends Usuario {
    public Administrador(String nome, String senha, String email, String cpf, String endereco, Telefone telefone ){
        super(nome, senha, email, cpf, endereco, telefone, Tipo.ADMIN);
    }
}