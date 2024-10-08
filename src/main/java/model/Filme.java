// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

import java.io.Serializable;

public class Filme implements Serializable {
    private String titulo;
    private String genero;
    private Data dataLancamento;
    private String diretor;
    private double preco;
    private boolean disponivel;

    public Filme(String titulo, String genero, Data dataLancamento, String diretor, double preco){
        this.titulo = titulo;
        this.genero =  genero;
        this.dataLancamento = dataLancamento;
        this.diretor = diretor;
        this.preco = preco;
        this.disponivel = true;
    }

    // Getters e setters
    public String getTitulo() {
        return this.titulo;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getDataLancamento() {
        return this.dataLancamento.toString();
    }

    public String getDiretor() {
        return this.diretor;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }
}
