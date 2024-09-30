package view;

import controller.GerenciamentoAdmin;
import model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciamentoAdminGUI {
    private GerenciamentoAdmin gerenciamentoAdmin;

    public GerenciamentoAdminGUI() {
        gerenciamentoAdmin = new GerenciamentoAdmin();
        criarTela();
    }

    private void criarTela() {
        JFrame frame = new JFrame("Sistema de Gerenciamento de Funcionários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(4, 2));

        JButton adicionarButton = new JButton("Adicionar Funcionário");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarFuncionario();
            }
        });
        painelEntrada.add(adicionarButton);

        JButton removerButton = new JButton("Remover Funcionário");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(frame, "Digite o CPF do funcionário a ser removido:");
                if (cpf != null && !cpf.trim().isEmpty()) {
                    Funcionario funcionarioARemover = localizarFuncionarioPorCPF(cpf);
                    if (funcionarioARemover != null) {
                        gerenciamentoAdmin.remover(funcionarioARemover);
                        JOptionPane.showMessageDialog(frame, "Funcionário removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Funcionário não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "CPF não pode ser vazio.");
                }
            }
        });
        painelEntrada.add(removerButton);

        JButton editarButton = new JButton("Editar Funcionário");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(frame, "Digite o CPF do funcionário a ser editado:");
                if (cpf != null && !cpf.trim().isEmpty()) {
                    Funcionario funcionarioAEditar = localizarFuncionarioPorCPF(cpf);
                    if (funcionarioAEditar != null) {
                        abrirTelaEditarFuncionario(funcionarioAEditar);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Funcionário não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "CPF não pode ser vazio.");
                }
            }
        });
        painelEntrada.add(editarButton);

        JButton listarButton = new JButton("Listar Funcionários");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Funcionario> funcionarios = gerenciamentoAdmin.listar();
                StringBuilder lista = new StringBuilder();
                if (funcionarios.isEmpty()) {
                    lista.append("Nenhum funcionário cadastrado.");
                } else {
                    for (Funcionario f : funcionarios) {
                        lista.append("Nome: ").append(f.getNome())
                                .append(", Email: ").append(f.getEmail())
                                .append(", CPF: ").append(f.getCpf())
                                .append(", Salário: ").append(f.getSalario()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, lista.toString());
            }
        });
        painelEntrada.add(listarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                frame.dispose();
            }
        });
        painelEntrada.add(voltarButton);

        frame.add(painelEntrada, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void abrirTelaAdicionarFuncionario() {
        JFrame adicionarFrame = new JFrame("Adicionar Funcionário");
        adicionarFrame.setSize(300, 300);
        adicionarFrame.setLayout(new GridLayout(0, 2));

        JTextField nomeField = new JTextField();
        JTextField senhaField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField enderecoField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField telefoneField = new JTextField();

        adicionarFrame.add(new JLabel("Nome:"));
        adicionarFrame.add(nomeField);
        adicionarFrame.add(new JLabel("Senha:"));
        adicionarFrame.add(senhaField);
        adicionarFrame.add(new JLabel("Email:"));
        adicionarFrame.add(emailField);
        adicionarFrame.add(new JLabel("CPF:"));
        adicionarFrame.add(cpfField);
        adicionarFrame.add(new JLabel("Endereço:"));
        adicionarFrame.add(enderecoField);
        adicionarFrame.add(new JLabel("Salário:"));
        adicionarFrame.add(salarioField);
        adicionarFrame.add(new JLabel("Telefone (código e número):"));
        adicionarFrame.add(telefoneField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = senhaField.getText();
                String email = emailField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                double salario = Double.parseDouble(salarioField.getText());
                String telefone = telefoneField.getText();

                Funcionario funcionario = new Funcionario(nome, senha, email, cpf, endereco, telefone, salario);
                gerenciamentoAdmin.adicionar(funcionario);
                JOptionPane.showMessageDialog(adicionarFrame, "Funcionário adicionado com sucesso!");
                adicionarFrame.dispose();
            }
        });

        adicionarFrame.add(salvarButton);
        adicionarFrame.setVisible(true);
    }

    private void abrirTelaEditarFuncionario(Funcionario funcionario) {
        JFrame editarFrame = new JFrame("Editar Funcionário");
        editarFrame.setSize(300, 300);
        editarFrame.setLayout(new GridLayout(0, 2));

        JTextField nomeField = new JTextField(funcionario.getNome());
        JTextField salarioField = new JTextField(String.valueOf(funcionario.getSalario()));
        JTextField emailField = new JTextField(funcionario.getEmail());
        JTextField enderecoField = new JTextField(funcionario.getEndereco());

        editarFrame.add(new JLabel("Nome:"));
        editarFrame.add(nomeField);
        editarFrame.add(new JLabel("Email:"));
        editarFrame.add(emailField);
        editarFrame.add(new JLabel("Endereço:"));
        editarFrame.add(enderecoField);
        editarFrame.add(new JLabel("Salário:"));
        editarFrame.add(salarioField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoNome = nomeField.getText();
                double novoSalario = Double.parseDouble(salarioField.getText());
                String novoEmail = emailField.getText();
                String novoEndereco = enderecoField.getText();

                funcionario.setNome(novoNome);
                funcionario.setSalario(novoSalario);
                funcionario.setEmail(novoEmail);
                funcionario.setEndereco(novoEndereco);

                gerenciamentoAdmin.editar(funcionario);

                JOptionPane.showMessageDialog(editarFrame, "Funcionário editado com sucesso!");
                editarFrame.dispose();
            }
        });

        editarFrame.add(salvarButton);
        editarFrame.setVisible(true);
    }

    private Funcionario localizarFuncionarioPorCPF(String cpf) {
        List<Funcionario> funcionarios = gerenciamentoAdmin.listar();
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }
}