package controller;

import java.util.List;

public interface ICrud<T> {
    void adicionar(T t);
    void remover(T t);
    void editar(T t);
    List<T> listar();
}
