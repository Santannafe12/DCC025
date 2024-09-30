// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package view;

import controller.GerenciamentoAdmin;
import model.Funcionario;
import model.Sessao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JLabel lblUsuario, lblSenha;
    private GerenciamentoAdmin gerenciamentoAdmin;

    public LoginGUI() {
        gerenciamentoAdmin = new GerenciamentoAdmin();
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 10, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 10, 160, 25);
        add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 40, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 40, 160, 25);
        add(txtSenha);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 80, 80, 25);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                if (validarLogin(usuario, senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido");
                    dispose();
                    Sessao.setAdmin("admin".equals(usuario) && "admin".equals(senha));
                    new MainGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Login inválido");
                }
            }
        });
    }

    private boolean validarLogin(String usuario, String senha) {
        if ("admin".equals(usuario) && "admin".equals(senha)) {
            return true;
        }

        List<Funcionario> funcionarios = gerenciamentoAdmin.listar();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(usuario) && funcionario.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;
    }
}
