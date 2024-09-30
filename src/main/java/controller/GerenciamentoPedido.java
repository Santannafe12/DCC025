// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package controller;

import model.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoPedido implements ICrud<Pedido> {
    private final String arquivoPedidos = "src" + File.separator + "main" + File.separator + "java" + File.separator + "persistence" + File.separator + "pedidos.dat";

    @Override
    public void adicionar(Pedido pedido) {
        List<Pedido> pedidos = listar();
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido() == pedido.getNumeroPedido()) {
                throw new IllegalArgumentException("Já existe um pedido com o número: " + pedido.getNumeroPedido());
            }
        }
        pedidos.add(pedido);
        salvarPedidos(pedidos);
    }

    @Override
    public void remover(Pedido pedido) {
        List<Pedido> pedidos = listar();
        pedidos.removeIf(p -> p.getNumeroPedido() == pedido.getNumeroPedido());
        salvarPedidos(pedidos);

        System.out.println("Pedidos após remoção:");
        for (Pedido p : pedidos) {
            System.out.println("Número do Pedido: " + p.getNumeroPedido() + ", Cliente: " + p.getCliente().getNome());
        }
    }

    @Override
    public void editar(Pedido pedidoEditado) {
        List<Pedido> pedidos = listar();
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (pedido.getNumeroPedido() == pedidoEditado.getNumeroPedido()) {
                pedido.getFilmes().clear();
                pedido.getFilmes().addAll(pedidoEditado.getFilmes());
                break;
            }
        }
        salvarPedidos(pedidos);
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        File arquivo = new File(arquivoPedidos);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de pedidos não encontrado, criando um novo.");
            return pedidos;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoPedidos))) {
            while (true) {
                try {
                    Pedido pedido = (Pedido) ois.readObject();
                    pedidos.add(pedido);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    private void salvarPedidos(List<Pedido> pedidos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoPedidos))) {
            for (Pedido pedido : pedidos) {
                oos.writeObject(pedido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
