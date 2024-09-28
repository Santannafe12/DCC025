package model;

public class Funcionario extends Usuario {
    private double salario;
    private String senha;

    public Funcionario(String nome, String perfil, String email, String cpf, String endereco, Telefone telefone, double salario) {
        super(nome, email, cpf, endereco, telefone, Tipo.FUNCIONARIO);
        this.salario = salario;
        this.senha = senha;
    }

    public void login() {
        // logica p login
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public double getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}
