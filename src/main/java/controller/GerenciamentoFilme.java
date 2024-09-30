// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package controller;

import model.Filme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoFilme implements ICrud<Filme> {
    private final String arquivoFilmes = "src" + File.separator + "main" + File.separator + "java" + File.separator + "persistence" + File.separator + "filmes.dat";

    @Override
    public void adicionar(Filme filme) {
        List<Filme> filmes = listar();
        for (Filme f : filmes) {
            if (f.getTitulo().equalsIgnoreCase(filme.getTitulo())) {
                throw new IllegalArgumentException("Já existe um filme cadastrado com o título: " + filme.getTitulo());
            }
        }
        filmes.add(filme);
        salvarFilmes(filmes);
    }

    @Override
    public void remover(Filme filme) {
        List<Filme> filmes = listar();
        filmes.removeIf(f -> f.getTitulo().equalsIgnoreCase(filme.getTitulo()));
        salvarFilmes(filmes);

        System.out.println("Filmes após remoção:");
        for (Filme f : filmes) {
            System.out.println("Título: " + f.getTitulo() + ", Diretor: " + f.getDiretor());
        }
    }

    @Override
    public void editar(Filme filmeEditado) {
        List<Filme> filmes = listar();
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            if (filme.getTitulo().equalsIgnoreCase(filmeEditado.getTitulo())) {
                filme.setPreco(filmeEditado.getPreco());
                break;
            }
        }
        salvarFilmes(filmes);
    }

    @Override
    public List<Filme> listar() {
        List<Filme> filmes = new ArrayList<>();
        File arquivo = new File(arquivoFilmes);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de filmes não encontrado, criando um novo.");
            return filmes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoFilmes))) {
            while (true) {
                try {
                    Filme filme = (Filme) ois.readObject();
                    filmes.add(filme);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    private void salvarFilmes(List<Filme> filmes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoFilmes))) {
            for (Filme filme : filmes) {
                oos.writeObject(filme);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
