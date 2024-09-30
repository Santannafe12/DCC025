// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

import exception.*;

import java.io.Serializable;
import java.util.regex.*;

public abstract class Usuario implements Serializable {
    private String nome;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;
    public enum Tipo{
        CLIENTE, FUNCIONARIO, ADMIN
    }

    private final Tipo cargo;

    public Usuario(String nome, String email, String cpf, String endereco, String telefone, Tipo cargo) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf.replaceAll("\\D", "");
        this.endereco = endereco;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public boolean validaEmail(String email) throws EmailException {
        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailException("Formato de e-mail inválido: " + email);
        }

        return true;
    }

    public boolean validaCpf(String cpf) throws CpfException {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

        if (!cpf.matches(regex)) {
            throw new CpfException("Formato de CPF inválido: " + cpf);
        }

        return true;
    }

    public boolean validaTelefone(String telefone) throws TelefoneException {
        String regex = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$";

        if (!telefone.matches(regex)) {
            throw new TelefoneException("Formato de telefone inválido: " + telefone);
        }

        return true;
    }

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
        if (this.cpf == null || this.cpf.length() < 11) {
            return "";
        }

        return this.cpf.substring(0, 3) + "." +
                this.cpf.substring(3, 6) + "." +
                this.cpf.substring(6, 9) + "-" +
                this.cpf.substring(9, 11);
    }

    public String getEndereco(){
        return this.endereco;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}