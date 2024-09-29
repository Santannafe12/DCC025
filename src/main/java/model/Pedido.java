package model;

import java.io.Serializable;
import java.util.*;

public class Pedido implements Serializable {
    private Cliente cliente;
    private int numeroPedido;
    private List<Filme> filmes;
    private double valorTotal;

    public Pedido(Cliente cliente, int numeroPedido) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.filmes = new ArrayList<>();
        this.valorTotal = 0;
    }

    public void adicionaFilme(Filme filme){
        if(filme.verificarDisponibilidade()){
            filmes.add(filme);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public double getValorTotal() {
        for (Filme filme : filmes) {
            this.valorTotal += filme.getPreco();
        }
        return valorTotal;
    }
}
