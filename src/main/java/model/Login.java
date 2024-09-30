// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

import java.util.HashMap;
import java.util.Map;

public class Login {
  private Map<String, String> usuarios;

    public Login() {
        usuarios = new HashMap<>();
        
        // Dados fictícios de usuários (email, senha) para simular login, remover depois
        usuarios.put("joao@email.com", "12345");
        usuarios.put("maria@email.com", "senha123");
    }

    public boolean validarLogin(String email, String senha) {
        if (usuarios.containsKey(email)) {
            String senhaArmazenada = usuarios.get(email);
            if (senhaArmazenada.equals(senha)) {
                System.out.println("Login bem-sucedido!");
                return true;
            } else {
                System.out.println("Senha incorreta!");
                return false;
            }
        } else {
            System.out.println("Usuário não encontrado!");
            return false;
        }
    }

    // Método para adicionar novos usuários ao sistema, remover depois
    public void adicionarUsuario(String email, String senha) {
        if (!usuarios.containsKey(email)) {
            usuarios.put(email, senha);
            System.out.println("Usuário adicionado com sucesso!");
        } else {
            System.out.println("Usuário já existe!");
        }
    }
}
