// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package controller;

import model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoCliente implements ICrud<Cliente> {
    private final String arquivoClientes = "src" + File.separator + "main" + File.separator + "java" + File.separator + "persistence" + File.separator + "clientes.dat";
    private List<Cliente> listaClientes = new ArrayList<>();

    @Override
    public void adicionar(Cliente cliente) {
        listaClientes = listar();
        for (Cliente c : listaClientes) {
            if (c.getCpf().equalsIgnoreCase(cliente.getCpf())) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com o CPF: " + cliente.getCpf());
            }
        }
        listaClientes.add(cliente);
        salvarClientes(listaClientes);
    }

    @Override
    public void remover(Cliente cliente) {
        listaClientes = listar();
        listaClientes.removeIf(c -> c.getCpf().equalsIgnoreCase(cliente.getCpf()));
        salvarClientes(listaClientes);

        System.out.println("Clientes após remoção:");
        for (Cliente c : listaClientes) {
            System.out.println("Nome: " + c.getNome() + ", CPF: " + c.getCpf());
        }
    }

    @Override
    public void editar(Cliente clienteEditado) {
        listaClientes = listar();
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if (cliente.getCpf().equalsIgnoreCase(clienteEditado.getCpf())) {
                cliente.setNome(clienteEditado.getNome());
                cliente.setEndereco(clienteEditado.getEndereco());
                break;
            }
        }
        salvarClientes(listaClientes);
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        File arquivo = new File(arquivoClientes);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de clientes não encontrado, criando um novo.");
            return clientes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoClientes))) {
            while (true) {
                try {
                    Cliente cliente = (Cliente) ois.readObject();
                    clientes.add(cliente);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente buscarPorCpf(String cpf) {
        listaClientes = listar();
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private void salvarClientes(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoClientes))) {
            for (Cliente cliente : clientes) {
                oos.writeObject(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
