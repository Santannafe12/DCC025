package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Tela Inicial");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JButton gerenciarFuncionarioButton = new JButton("Gerenciar Funcionários");
        gerenciarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciamentoAdminGUI();
                dispose();
            }
        });

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

        add(gerenciarFuncionarioButton);
        add(gerenciarClienteButton);
        add(gerenciarFilmeButton);
        add(gerenciarPedidoButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
