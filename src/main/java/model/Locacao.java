package model;

public class Locacao {
    private Cliente cliente;
    private int numeroPedido;
    private Filme filme;
    private int quantidade;
    private double valorTotal;
    private Data dataDeDevolucao; 

    public Locacao(Cliente cliente, int numeroPedido, Filme filme, int quantidade, double valorTotal, Data dataDeDevolucao) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.filme = filme;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Data getDataDeDevolucao() {
        return dataDeDevolucao;
    }
}
