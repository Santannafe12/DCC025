package model;

public class Funcionario extends Usuario {
    private double salario;

    public Funcionario(String nome, String senha, String perfil, String email, String cpf, String endereco, Telefone telefone, double salario) {
        super(nome, senha, email, cpf, endereco, telefone, Tipo.FUNCIONARIO);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }
}
