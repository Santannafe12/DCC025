package model;

public class Cliente extends Usuario{
    public Cliente(String nome, String senha, String perfil, String email, String cpf) {
        super(nome, senha, perfil, email, cpf, false);
    }
}
