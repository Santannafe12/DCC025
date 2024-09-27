package controller;

import exception.CpfInvalidoException;

public class Validador {
    public static void validarCpf(String cpf) throws CpfInvalidoException {
        if (cpf == null || cpf.length() != 11) {
            throw new CpfInvalidoException("CPF inválido. Deve conter 11 dígitos.");
        }
        // validacao
    }
}
