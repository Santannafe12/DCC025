package controller;

import model.Administrador;
import java.util.*;

public class GerenciamentoAdmin {
    private List<Administrador> administradores; // Renomeado para plural

    public GerenciamentoAdmin() {
        administradores = new ArrayList<>();
    }

    // Método para adicionar um administrador
    public void adicionaAdmin(Administrador admin) {
        administradores.add(admin); // Corrigido para usar a lista
    }

    // Método para remover um administrador
    public void removeAdmin(Administrador admin) {
        administradores.remove(admin); // Corrigido para usar a lista
    }

    // Método para listar administradores (opcional)
    public List<Administrador> getAdministradores() {
        return administradores;
    }
}
