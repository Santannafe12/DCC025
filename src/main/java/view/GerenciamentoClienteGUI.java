// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package view;

import controller.GerenciamentoCliente;
import exception.CpfException;
import exception.EmailException;
import exception.TelefoneException;
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

        JPanel painelEntrada = new JPanel(new GridLayout(8, 2));

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
                String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser removido:");
                if (cpf != null && !cpf.trim().isEmpty()) {
                    Cliente clienteARemover = localizarClientePorCPF(cpf);
                    if (clienteARemover != null) {
                        gerenciamentoCliente.remover(clienteARemover);
                        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não pode ser vazio.");
                }
            }
        });
        painelEntrada.add(removerButton);

        JButton editarButton = new JButton("Editar Cliente");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser editado:");
                if (cpf != null && !cpf.trim().isEmpty()) {
                    Cliente clienteAEditar = localizarClientePorCPF(cpf);
                    if (clienteAEditar != null) {
                        abrirTelaEditarCliente(clienteAEditar);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não pode ser vazio.");
                }
            }
        });
        painelEntrada.add(editarButton);

        JButton listarButton = new JButton("Listar Clientes");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });
        painelEntrada.add(listarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                GerenciamentoClienteGUI.this.dispose();
            }
        });
        painelEntrada.add(voltarButton);

        add(painelEntrada, BorderLayout.CENTER);
        setVisible(true);
    }

    private void adicionarCliente() {
        String nome = nomeField.getText().trim();
        String email = emailField.getText().trim();
        String cpf = cpfField.getText().trim();
        String endereco = enderecoField.getText().trim();
        String telefone = telefoneField.getText().trim();

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, email, cpf, endereco, telefone, Usuario.Tipo.CLIENTE);

        try {
            cliente.validaEmail(email);
            cliente.validaCpf(cpf);
            cliente.validaTelefone(telefone);
            gerenciamentoCliente.adicionar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
        } catch (EmailException | CpfException | TelefoneException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaEditarCliente(Cliente cliente) {
        JTextField nomeField = new JTextField(cliente.getNome());
        JTextField emailField = new JTextField(cliente.getEmail());
        JTextField enderecoField = new JTextField(cliente.getEndereco());
        JTextField telefoneField = new JTextField(cliente.getTelefone());

        JPanel painelEditar = new JPanel(new GridLayout(5, 2));
        painelEditar.add(new JLabel("Nome:"));
        painelEditar.add(nomeField);
        painelEditar.add(new JLabel("Email:"));
        painelEditar.add(emailField);
        painelEditar.add(new JLabel("Endereço:"));
        painelEditar.add(enderecoField);
        painelEditar.add(new JLabel("Telefone:"));
        painelEditar.add(telefoneField);

        int option = JOptionPane.showConfirmDialog(null, painelEditar, "Editar Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String novoNome = nomeField.getText().trim();
            String novoEmail = emailField.getText().trim();
            String novoEndereco = enderecoField.getText().trim();
            String novoTelefone = telefoneField.getText().trim();

            try {
                cliente.validaEmail(novoEmail);
                cliente.validaTelefone(novoTelefone);
                cliente.setNome(novoNome);
                cliente.setEmail(novoEmail);
                cliente.setEndereco(novoEndereco);
                cliente.setTelefone(novoTelefone);
                gerenciamentoCliente.editar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
            } catch (EmailException | TelefoneException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Dados inválidos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = gerenciamentoCliente.listar();
        StringBuilder lista = new StringBuilder();
        if (clientes.isEmpty()) {
            lista.append("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                lista.append("Nome: ").append(c.getNome())
                        .append(", Email: ").append(c.getEmail())
                        .append(", CPF: ").append(c.getCpf())
                        .append(", Endereço: ").append(c.getEndereco())
                        .append(", Telefone: ").append(c.getTelefone()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    private Cliente localizarClientePorCPF(String cpf) {
        List<Cliente> clientes = gerenciamentoCliente.listar();
        for (Cliente c : clientes) {
            if (c.getCpf() != null && c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}
