package model;

public class Cliente extends Usuario{
    public Cliente(String nome, String email, String cpf, String endereco, Telefone telefone, Tipo cargo) {
        super(nome, email, cpf, endereco, telefone, Tipo.CLIENTE);
    }
}
