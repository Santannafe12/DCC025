package view;

import controller.GerenciamentoAdmin; // Importa a classe de gerenciamento
import model.Funcionario; // Importa o modelo de funcionário

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JLabel lblUsuario, lblSenha;
    private GerenciamentoAdmin gerenciamentoAdmin; // Classe de gerenciamento de funcionários

    public LoginGUI() {
        gerenciamentoAdmin = new GerenciamentoAdmin(); // Instancia o gerenciamento de funcionários
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblUsuario = new JLabel("Usuário(CPF):");
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
                    dispose(); // Fecha a tela de login
                    // Chama MainGUI com isAdmin verdadeiro para admin, falso para funcionários
                    boolean isAdmin = "admin".equals(usuario) && "123".equals(senha);
                    new MainGUI(isAdmin); // Passa a informação se é admin ou não
                } else {
                    JOptionPane.showMessageDialog(null, "Login inválido");
                }
            }
        });
    }

    private boolean validarLogin(String usuario, String senha) {
        // Validação do usuário padrão
        if ("admin".equals(usuario) && "123".equals(senha)) {
            return true;
        }

        // Validação para funcionários cadastrados
        List<Funcionario> funcionarios = gerenciamentoAdmin.listar(); // Lista de funcionários cadastrados
//        System.out.println("Funcionários cadastrados: " + funcionarios); // Para depuração
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Verificando: CPF = " + funcionario.getCpf() + ", Senha = " + funcionario.getSenha()); // Para depuração
            if (funcionario.getCpf().equals(usuario) && funcionario.getSenha().equals(senha)) {
                return true; // Login bem-sucedido com um funcionário
            }
        }

        return false; // Login falhou
    }


    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}
