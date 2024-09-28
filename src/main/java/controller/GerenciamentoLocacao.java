package controller;

import model.Funcionario;
import java.util.*;

public class GerenciamentoLocacao {
    List<Locacao> locacao;

    public GerenciamentoLocacao(){
        locacao = new ArrayList<>();
    }

    public void adicionaLocacao(Locacao locacao){
        locacao.add(locacao);
    }

    public void removeLocacao(Locacao locacao){
        locacao.remove(locacao);
    }
}