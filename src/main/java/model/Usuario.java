package model;

import exception.*;
import java.util.regex.*;

public abstract class Usuario {
    private String nome;
    private String senha;
    private String perfil;
    private String email;
    private String cpf;
    private boolean tipo;

    public Usuario(String nome, String senha, String perfil, String email, String cpf, boolean tipo) {
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
        if(validaEmail(email)){
            this.email = email;
        }
        if(validaCpf(cpf)){
            this.cpf = cpf.replaceAll("[^\\d]", "");
        }
        this.tipo = tipo;
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

    public boolean getTipo(){
        return this.tipo;
    }

    public String getCpf() {
        return this.cpf.substring(0, 3) + "." + 
                this.cpf.substring(3, 6) + "." + 
                this.cpf.substring(6, 9) + "-" + 
                this.cpf.substring(9, 11);
    }
    
}