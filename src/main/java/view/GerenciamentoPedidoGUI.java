// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package view;

import controller.GerenciamentoCliente;
import controller.GerenciamentoPedido;
import controller.GerenciamentoFilme;
import model.Cliente;
import model.Filme;
import model.Pedido;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenciamentoPedidoGUI extends JFrame {
    private final GerenciamentoPedido gerenciamentoPedido;
    private final GerenciamentoCliente gerenciamentoCliente;
    private final GerenciamentoFilme gerenciamentoFilme;
    private JTextField numeroPedidoField, clienteCpfField, filmeTituloField;
    private JTextArea listaPedidosArea;

    public GerenciamentoPedidoGUI() {
        gerenciamentoPedido = new GerenciamentoPedido();
        gerenciamentoCliente = new GerenciamentoCliente();
        gerenciamentoFilme = new GerenciamentoFilme();
        criarTela();
    }

    private void criarTela() {
        setTitle("Gerenciamento de Pedidos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(9, 2));

        painelEntrada.add(new JLabel("Número do Pedido:"));
        numeroPedidoField = new JTextField();
        painelEntrada.add(numeroPedidoField);

        painelEntrada.add(new JLabel("CPF do Cliente:"));
        clienteCpfField = new JTextField();
        painelEntrada.add(clienteCpfField);

        painelEntrada.add(new JLabel("Título do Filme:"));
        filmeTituloField = new JTextField();
        painelEntrada.add(filmeTituloField);

        JButton adicionarButton = new JButton("Adicionar Pedido");
        adicionarButton.addActionListener(e -> adicionarPedido());
        painelEntrada.add(adicionarButton);

        JButton removerButton = new JButton("Remover Pedido");
        removerButton.addActionListener(e -> removerPedido());
        painelEntrada.add(removerButton);

        JButton editarButton = new JButton("Editar Pedido");
        editarButton.addActionListener(e -> editarPedido());
        painelEntrada.add(editarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new MainGUI();
            dispose();
        });
        painelEntrada.add(voltarButton);

        listaPedidosArea = new JTextArea();
        listaPedidosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaPedidosArea);

        JButton listarButton = new JButton("Listar Pedidos");
        listarButton.addActionListener(e -> listarPedidos());

        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void adicionarPedido() {
        String numeroPedido = numeroPedidoField.getText();
        String cpfCliente = clienteCpfField.getText();
        String tituloFilme = filmeTituloField.getText();

        if (numeroPedido.isEmpty() || cpfCliente.isEmpty() || tituloFilme.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = gerenciamentoCliente.buscarPorCpf(cpfCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado com o CPF: " + cpfCliente, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Filme filme = localizarFilmePorTitulo(tituloFilme);
        if (filme == null) {
            JOptionPane.showMessageDialog(this, "Filme não encontrado com o título: " + tituloFilme, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pedido pedido = new Pedido(cliente, Integer.parseInt(numeroPedido));
        pedido.adicionaFilme(filme);

        try {
            gerenciamentoPedido.adicionar(pedido);
            JOptionPane.showMessageDialog(this, "Pedido adicionado com sucesso!");
            limparCampos();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Filme localizarFilmePorTitulo(String titulo) {
        return gerenciamentoFilme.listar().stream()
                .filter(f -> f.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    private void removerPedido() {
        String numeroPedido = JOptionPane.showInputDialog(this, "Digite o número do pedido a ser removido:");
        if (numeroPedido == null || numeroPedido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o número do pedido a ser removido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Pedido pedido = new Pedido(null, Integer.parseInt(numeroPedido));
            gerenciamentoPedido.remover(pedido);
            JOptionPane.showMessageDialog(this, "Pedido removido com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número do pedido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPedido() {
        String numeroPedido = JOptionPane.showInputDialog(this, "Digite o número do pedido a ser editado:");
        if (numeroPedido == null || numeroPedido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o número do pedido a ser editado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoTituloFilme = JOptionPane.showInputDialog(this, "Digite o novo título do filme:");
        if (novoTituloFilme == null || novoTituloFilme.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o novo título do filme.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Pedido pedido = new Pedido(null, Integer.parseInt(numeroPedido));
            gerenciamentoPedido.editar(pedido);
            JOptionPane.showMessageDialog(this, "Pedido editado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número do pedido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPedidos() {
        List<Pedido> pedidos = gerenciamentoPedido.listar();
        StringBuilder sb = new StringBuilder();
        if (pedidos.isEmpty()) {
            sb.append("Nenhum pedido cadastrado.");
        } else {
            for (Pedido p : pedidos) {
                sb.append("Número do Pedido: ").append(p.getNumeroPedido())
                        .append(", Cliente: ").append(p.getCliente().getNome())
                        .append(", Filmes: ").append(p.getFilmes().stream().map(Filme::getTitulo).toList()).append("\n");
            }
        }
        listaPedidosArea.setText(sb.toString());
    }

    private void limparCampos() {
        numeroPedidoField.setText("");
        clienteCpfField.setText("");
        filmeTituloField.setText("");
    }
}
