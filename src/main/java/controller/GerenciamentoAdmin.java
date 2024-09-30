// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package controller;

import model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoAdmin implements ICrud<Funcionario> {
    private final String arquivoFuncionarios = "src" + File.separator + "main" + File.separator + "java" + File.separator + "persistence" + File.separator + "funcionarios.dat";

    @Override
    public void adicionar(Funcionario funcionario) {
        List<Funcionario> funcionarios = listar();
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(funcionario.getCpf())) {
                throw new IllegalArgumentException("Já existe um funcionário cadastrado com o CPF: " + funcionario.getCpf());
            }
        }
        funcionarios.add(funcionario);
        salvarFuncionarios(funcionarios);
    }

    @Override
    public void remover(Funcionario funcionario) {
        List<Funcionario> funcionarios = listar();
        funcionarios.remove(funcionario);
        salvarFuncionarios(funcionarios);

        System.out.println("Funcionários após remoção:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", CPF: " + f.getCpf());
        }
    }

    @Override
    public void editar(Funcionario funcionarioEditado) {
        List<Funcionario> funcionarios = listar();
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            if (funcionario.getCpf().equals(funcionarioEditado.getCpf())) {
                funcionario.setNome(funcionarioEditado.getNome());
                funcionario.setSalario(funcionarioEditado.getSalario());
                funcionario.setEmail(funcionarioEditado.getEmail());
                funcionario.setEndereco(funcionarioEditado.getEndereco());
                break;
            }
        }
        salvarFuncionarios(funcionarios);
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        File arquivo = new File(arquivoFuncionarios);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de funcionários não encontrado, criando um novo.");
            return funcionarios;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoFuncionarios))) {
            while (true) {
                try {
                    Funcionario funcionario = (Funcionario) ois.readObject();
                    funcionarios.add(funcionario);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    private void salvarFuncionarios(List<Funcionario> funcionarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoFuncionarios))) {
            for (Funcionario funcionario : funcionarios) {
                oos.writeObject(funcionario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
