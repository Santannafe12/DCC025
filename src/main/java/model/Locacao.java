package model;

import java.util.*;

public class Locacao {
    private Cliente cliente;
    private int numeroPedido;
    private List<Filme> filmes;
    private double valorTotal;
    private Data dataDeDevolucao; 

    public Locacao(Cliente cliente, int numeroPedido, Filme filme, int quantidade, double valorTotal, Data dataDeDevolucao) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.filmes = new ArrayList<>();
        this.valorTotal = valorTotal;
        this.dataDeDevolucao = dataDeDevolucao;
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
        return valorTotal;
    }

    public Data getDataDeDevolucao() {
        return dataDeDevolucao;
    }
}
