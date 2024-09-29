package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    public MainGUI(boolean isAdmin) { // Adiciona um parâmetro para verificar se é administrador
        setTitle("Tela Inicial");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        if (isAdmin) { // Se for admin, mostra o botão de gerenciar funcionários
            JButton gerenciarFuncionarioButton = new JButton("Gerenciar Funcionários");
            gerenciarFuncionarioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new GerenciamentoAdminGUI();
                    dispose();
                }
            });
            add(gerenciarFuncionarioButton); // Adiciona o botão de gerenciar funcionários
        }

        JButton gerenciarClienteButton = new JButton("Gerenciar Clientes");
        gerenciarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciamentoClienteGUI();
                dispose();
            }
        });

        JButton gerenciarFilmeButton = new JButton("Gerenciar Filmes");
        gerenciarFilmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciamentoFilmeGUI();
                dispose();
            }
        });

        JButton gerenciarPedidoButton = new JButton("Gerenciar Pedidos");
        gerenciarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciamentoPedidoGUI();
                dispose();
            }
        });

        add(gerenciarClienteButton);
        add(gerenciarFilmeButton);
        add(gerenciarPedidoButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI(true); // Exemplo de chamada com admin como false
    }
}