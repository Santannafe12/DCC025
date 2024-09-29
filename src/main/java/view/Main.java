package view;

import controller.GerenciamentoAdmin; // Importar a classe correta
import model.Administrador; // Importar a classe correta
import model.Telefone;
import exception.TelefoneException;

public class Main {
    public static void main(String[] args) {
        // Criar uma instância do gerenciamento de administradores
        GerenciamentoAdmin gerenciamento = new GerenciamentoAdmin();
        
        try {
            Telefone telefone = Telefone.parser("(24)99999-9999");
            Administrador admin = new Administrador("admin", "admin@example.com", "admin", "123.456.789-00", "rua chique-chique", telefone);
                    // Adicionar o administrador
            gerenciamento.adicionaAdmin(admin);

            // Remover o administrador
//            gerenciamento.removeAdmin(admin);
            // Continue com o restante do seu código
        } catch (TelefoneException e) {
            System.out.println("Erro ao criar telefone: " + e.getMessage());
        }
       
        // Iniciar a tela de login
        LoginGUI loginGUI = new LoginGUI();
        
        // Definir visibilidade
        loginGUI.setVisible(true);

        // Aqui você pode definir como o fluxo do programa continuará
        // após o login, verificando se o usuário é admin ou funcionário
    }
}
