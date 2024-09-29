package controller;

import model.Locacao;

import java.util.*;

public class GerenciamentoLocacao {
    List<Locacao> locacao;

    public GerenciamentoLocacao(){
        locacao = new ArrayList<>();
    }

    public void adicionaLocacao(Locacao novaLocacao){
        locacao.add(novaLocacao);
    }

    public void removeLocacao(Locacao novaLocacao){
        locacao.remove(novaLocacao);
    }
}