package model;

import exception.*;
import java.util.regex.*;

public abstract class Usuario {
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private String endereco;
    private Telefone telefone;
    public enum Tipo{
        CLIENTE, FUNCIONARIO, ADMIN;
    };
    private Tipo cargo;

    public Usuario(String nome, String senha, String email, String cpf, String endereco, Telefone telefone, Tipo cargo) {
        this.nome = nome;
        this.senha = senha;
        if(validaEmail(email)){
            this.email = email;
        }
        if(validaCpf(cpf)){
            this.cpf = cpf.replaceAll("[^\\d]", "");
        }
        this.endereco = endereco;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public abstract void login();

    private boolean validaEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");
        
        try {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new EmailException("Formato de e-mail inválido: " + email);
            }
            return true;
        } catch (EmailException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        try {
            if (cpf.length() != 11) {
                throw new CpfException("CPF inválido: " + cpf);
            }
            return true;
        } catch (CpfException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Getters e setters
    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public Tipo getCargo(){
        return this.cargo;
    }

    public String getCpf() {
        return this.cpf.substring(0, 3) + "." + 
                this.cpf.substring(3, 6) + "." + 
                this.cpf.substring(6, 9) + "-" + 
                this.cpf.substring(9, 11);
    }

    public String getEndereco(){
        return this.endereco;
    }

    public String getTelefone(){
        return this.telefone.toString();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}