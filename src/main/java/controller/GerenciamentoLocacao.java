package controller;

import model.Locacao;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoLocacao {
    private List<Locacao> locacoes;

    public GerenciamentoLocacao() {
        this.locacoes = new ArrayList<>(); // Inicializa a lista de locações
    }

    // Método para adicionar uma locação
    public void addLocacao(Locacao locacao) {
        locacoes.add(locacao); // Adiciona a locação à lista
    }

    // Método para remover uma locação
    public void removeLocacao(Locacao locacao) {
        locacoes.remove(locacao); // Remove a locação da lista
    }

    // Outros métodos para gerenciar locações podem ser adicionados aqui
}
