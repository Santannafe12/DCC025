// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

public class Cliente extends Usuario{
    public Cliente(String nome, String email, String cpf, String endereco, String telefone, Tipo cargo) {
        super(nome, email, cpf, endereco, telefone, Tipo.CLIENTE);
    }
}
