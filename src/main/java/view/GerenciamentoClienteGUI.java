package view;

import controller.GerenciamentoCliente;
import model.Cliente;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciamentoClienteGUI extends JFrame {
    private final GerenciamentoCliente gerenciamentoCliente;
    private JTextField nomeField, emailField, cpfField, enderecoField, telefoneField;
    private JTextArea listaClientesArea;

    public GerenciamentoClienteGUI() {
        gerenciamentoCliente = new GerenciamentoCliente();
        criarTela();
    }

    private void criarTela() {
        setTitle("Gerenciamento de Clientes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(7, 2));

        painelEntrada.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        painelEntrada.add(nomeField);

        painelEntrada.add(new JLabel("Email:"));
        emailField = new JTextField();
        painelEntrada.add(emailField);

        painelEntrada.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        painelEntrada.add(cpfField);

        painelEntrada.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        painelEntrada.add(enderecoField);

        painelEntrada.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        painelEntrada.add(telefoneField);

        JButton adicionarButton = new JButton("Adicionar Cliente");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        painelEntrada.add(adicionarButton);

        JButton removerButton = new JButton("Remover Cliente");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });
        painelEntrada.add(removerButton);

        JButton editarButton = new JButton("Editar Cliente");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        painelEntrada.add(editarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI(false);
                dispose();
            }
        });
        add(voltarButton);
        painelEntrada.add(voltarButton);

        listaClientesArea = new JTextArea();
        listaClientesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaClientesArea);

        JButton listarButton = new JButton("Listar Clientes");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void adicionarCliente() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        Usuario.Tipo cargo = Usuario.Tipo.CLIENTE;

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, email, cpf, endereco, telefone, cargo);
        try {
            gerenciamentoCliente.adicionar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
            limparCampos();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCliente() {
        String cpf = cpfField.getText();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser removido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente("", "", cpf, "", null, Usuario.Tipo.CLIENTE);
        gerenciamentoCliente.remover(cliente);
        JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
        limparCampos();
    }

    private void editarCliente() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser editado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, email, cpf, endereco, telefone, Usuario.Tipo.CLIENTE);
        gerenciamentoCliente.editar(cliente);
        JOptionPane.showMessageDialog(this, "Cliente editado com sucesso!");
        limparCampos();
    }

    private void listarClientes() {
        List<Cliente> clientes = gerenciamentoCliente.listar();
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append("Nome: ").append(c.getNome())
                    .append(", Email: ").append(c.getEmail())
                    .append(", CPF: ").append(c.getCpf())
                    .append(", Endereço: ").append(c.getEndereco())
                    .append(", Telefone: ").append(c.getTelefone())
                    .append("\n");
        }
        listaClientesArea.setText(sb.toString());
    }

    private void limparCampos() {
        nomeField.setText("");
        emailField.setText("");
        cpfField.setText("");
        enderecoField.setText("");
        telefoneField.setText("");
    }
}
