/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author CALEBE
 */ 
    

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame {

    // Componentes da interface
    private JTextField nomeField;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JComboBox<String> perfilBox;
    private JButton cadastrarButton;

    public Cadastro() {
        // Configurando a janela
        super("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));
        
        // Componentes da tela
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel senhaLabel = new JLabel("Senha:");
        JLabel perfilLabel = new JLabel("Perfil:");

        nomeField = new JTextField();
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        // Combobox para escolher perfil (funcionário ou administrador)
        perfilBox = new JComboBox<>(new String[]{"Funcionário", "Administrador"});
        
        cadastrarButton = new JButton("Cadastrar");

        // Adicionando os componentes à janela
        add(nomeLabel);
        add(nomeField);
        add(cpfLabel);
        add(cpfField);
        add(senhaLabel);
        add(senhaField);
        add(perfilLabel);
        add(perfilBox);
        add(new JLabel()); // Espaço vazio
        add(cadastrarButton);
        
        // Ação do botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String senha = new String(senhaField.getPassword());
                String perfil = (String) perfilBox.getSelectedItem();
                
                // Aqui você implementaria a lógica de cadastro, por exemplo:
                // Verificar se o CPF já existe, validar os campos, etc.
                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    // Cadastrar usuário (salvar em arquivo ou banco de dados)
                    JOptionPane.showMessageDialog(null, "Usuário " + nome + " cadastrado com sucesso como " + perfil + "!");
                    dispose(); // Fechar janela após o cadastro
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cadastro cadastro = new Cadastro();
            cadastro.setVisible(true);
        });
    }
}

