// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package controller;

import java.util.List;

public interface ICrud<T> {
    void adicionar(T t);
    void remover(T t);
    void editar(T t);
    List<T> listar();
}
