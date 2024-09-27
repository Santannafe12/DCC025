package model;

public class Funcionario extends Usuario {
    public Funcionario(String nome, String senha, String perfil, String email, String cpf) {
        super(nome, senha, perfil, email, cpf, false);
    }

    public void login() {
        // logica p login
    }

    // metodos p administradores
}
