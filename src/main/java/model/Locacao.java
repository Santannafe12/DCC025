package model;

import java.util.*;

public class Locacao {
    private Cliente cliente;
    private int numeroPedido;
    private List<Filme> filmes;
    private double valorTotal;
    private Data dataDeDevolucao; 

    public Locacao(Cliente cliente, int numeroPedido, Data dataDeDevolucao) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.filmes = new ArrayList<>();
        this.valorTotal = 0;
        this.dataDeDevolucao = dataDeDevolucao;
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

    public Data getDataDeDevolucao() {
        return dataDeDevolucao;
    }
}
