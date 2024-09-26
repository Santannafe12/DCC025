package controller;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServico implements ICrud<Cliente> {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void adicionar(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void remover(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    public void editar(Cliente cliente) {
        // logica
    }

    public List<Cliente> listar() {
        return listaClientes;
    }
}
