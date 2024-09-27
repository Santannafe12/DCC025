package model;

public abstract class Usuario {
    private String nome;
    private String senha;
    private String perfil;

    public Usuario(String nome, String senha, String perfil) {
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
    }

    public abstract void login();

    // get e set
}
