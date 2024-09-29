package controller;

import model.Funcionario;
import java.util.*;

public class GerenciamentoAdmin {
    List<Funcionario> funcionarios;

    public GerenciamentoAdmin(){
        funcionarios = new ArrayList<>();
    }

    public void adicionaFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public void removeFuncionario(Funcionario funcionario){
        funcionarios.remove(funcionario);
    }
}