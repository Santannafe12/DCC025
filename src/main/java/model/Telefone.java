package model;

import java.io.Serializable;

import exception.*;

public class Telefone implements Serializable {
    private int ddd;
    private int numero;

    public Telefone(int ddd, int numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public static Telefone parser(String telefone) throws TelefoneException {
        try {
            if (!telefone.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}")) {
                throw new TelefoneException("Formato de telefone inválido");
            }
            String[] partes = telefone.replaceAll("[()\\-]", "").split("(?<=\\d{2})");
            int ddd = Integer.parseInt(partes[0]);
            int numero = Integer.parseInt(partes[1]);

            return new Telefone(ddd, numero);

        } catch (TelefoneException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;

        }
    }

    public int getDdd() {
        return ddd;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        String numeroStr = String.valueOf(numero);

        if (numeroStr.length() == 8) {
            numeroStr = numeroStr.substring(0, 4) + "-" + numeroStr.substring(4);
        } else if (numeroStr.length() == 9) {
            numeroStr = numeroStr.substring(0, 5) + "-" + numeroStr.substring(5);
        }

        return String.format("(%02d) %s", ddd, numeroStr);
    }
}