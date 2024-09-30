package view;

import controller.GerenciamentoFilme;
import model.Data;
import model.Filme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciamentoFilmeGUI {
    private GerenciamentoFilme gerenciamentoFilme;

    public GerenciamentoFilmeGUI() {
        gerenciamentoFilme = new GerenciamentoFilme();
        criarTela();
    }

    private void criarTela() {
        JFrame frame = new JFrame("Sistema de Gerenciamento de Filmes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JButton adicionarButton = new JButton("Adicionar Filme");
        JButton removerButton = new JButton("Remover Filme");
        JButton editarButton = new JButton("Editar Filme");
        JButton listarButton = new JButton("Listar Filmes");

        JButton voltarButton = new JButton("Voltar");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarFilme();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(frame, "Digite o título do filme a ser removido:");

                if (titulo != null && !titulo.trim().isEmpty()) {
                    Filme filmeARemover = localizarFilmePorTitulo(titulo);
                    if (filmeARemover != null) {
                        gerenciamentoFilme.remover(filmeARemover);
                        JOptionPane.showMessageDialog(frame, "Filme removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Filme não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "O título não pode ser vazio.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(frame, "Digite o título do filme a ser editado:");
                if (titulo != null && !titulo.trim().isEmpty()) {
                    Filme filmeAEditar = localizarFilmePorTitulo(titulo);
                    if (filmeAEditar != null) {
                        abrirTelaEditarFilme(filmeAEditar);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Filme não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "O título não pode ser vazio.");
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Filme> filmes = gerenciamentoFilme.listar();
                StringBuilder lista = new StringBuilder();
                if (filmes.isEmpty()) {
                    lista.append("Nenhum filme cadastrado.");
                } else {
                    for (Filme f : filmes) {
                        lista.append("Título: ").append(f.getTitulo())
                                .append(", Gênero: ").append(f.getGenero())
                                .append(", Diretor: ").append(f.getDiretor())
                                .append(", Preço: ").append(f.getPreco()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, lista.toString());
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                frame.dispose();
            }
        });

        frame.add(adicionarButton);
        frame.add(removerButton);
        frame.add(editarButton);
        frame.add(listarButton);
        frame.add(voltarButton);

        frame.setVisible(true);
    }

    private void abrirTelaAdicionarFilme() {
        JFrame adicionarFrame = new JFrame("Adicionar Filme");
        adicionarFrame.setSize(300, 300);
        adicionarFrame.setLayout(new GridLayout(0, 2));

        JTextField tituloField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField dataLancamentoField = new JTextField();
        JTextField diretorField = new JTextField();
        JTextField precoField = new JTextField();

        adicionarFrame.add(new JLabel("Título:"));
        adicionarFrame.add(tituloField);
        adicionarFrame.add(new JLabel("Gênero:"));
        adicionarFrame.add(generoField);
        adicionarFrame.add(new JLabel("Data de Lançamento (dd/MM/yyyy):"));
        adicionarFrame.add(dataLancamentoField);
        adicionarFrame.add(new JLabel("Diretor:"));
        adicionarFrame.add(diretorField);
        adicionarFrame.add(new JLabel("Preço:"));
        adicionarFrame.add(precoField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = tituloField.getText();
                    String genero = generoField.getText();
                    String[] dataParts = dataLancamentoField.getText().split("/");
                    String dataLancamentoText = dataLancamentoField.getText();
                    if (!isValidDate(dataLancamentoText)) {
                        throw new IllegalArgumentException("Data deve estar no formato dd/MM/yyyy.");
                    }
                    Data dataLancamento = new Data(Integer.parseInt(dataParts[0]), Integer.parseInt(dataParts[1]), Integer.parseInt(dataParts[2]));
                    String diretor = diretorField.getText();
                    double preco = Double.parseDouble(precoField.getText());

                    Filme filme = new Filme(titulo, genero, dataLancamento, diretor, preco);
                    gerenciamentoFilme.adicionar(filme);
                    JOptionPane.showMessageDialog(adicionarFrame, "Filme adicionado com sucesso!");
                    adicionarFrame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(adicionarFrame, "Erro ao adicionar o filme: " + ex.getMessage());
                }
            }
        });

        adicionarFrame.add(salvarButton);
        adicionarFrame.setVisible(true);
    }

    private boolean isValidDate(String date) {
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";
        return date.matches(datePattern);
    }


    private void abrirTelaEditarFilme(Filme filme) {
        JFrame editarFrame = new JFrame("Editar Filme");
        editarFrame.setSize(300, 300);
        editarFrame.setLayout(new GridLayout(0, 2));

        JTextField tituloField = new JTextField(filme.getTitulo());
        JTextField generoField = new JTextField(filme.getGenero());
        JTextField precoField = new JTextField(String.valueOf(filme.getPreco()));

        editarFrame.add(new JLabel("Título:"));
        editarFrame.add(tituloField);
        editarFrame.add(new JLabel("Gênero:"));
        editarFrame.add(generoField);
        editarFrame.add(new JLabel("Preço:"));
        editarFrame.add(precoField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filme.setPreco(Double.parseDouble(precoField.getText()));
                gerenciamentoFilme.editar(filme);
                JOptionPane.showMessageDialog(editarFrame, "Filme editado com sucesso!");
                editarFrame.dispose();
            }
        });

        editarFrame.add(salvarButton);
        editarFrame.setVisible(true);
    }

    private Filme localizarFilmePorTitulo(String titulo) {
        List<Filme> filmes = gerenciamentoFilme.listar();
        for (Filme f : filmes) {
            if (f.getTitulo().equalsIgnoreCase(titulo)) {
                return f;
            }
        }
        return null;
    }
}
