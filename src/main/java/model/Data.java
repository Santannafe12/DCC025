// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

import exception.*;

import java.io.Serializable;

public class Data implements Serializable {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static Data dataParser(String data) throws DataException {
        try {
            String[] partes = data.split("/");

            if (partes.length != 3) {
                throw new DataException("Formato de data inválido! Esperado: DD/MM/AAAA");
            }

            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);

            if ((dia >= 1 && dia <= 30) && (mes >= 1 && mes <= 12) && (ano <= 2024)) {
                return new Data(dia, mes, ano);
            } else {
                throw new DataException("Data inválida!");
            }
        } catch (NumberFormatException e) {
            throw new DataException("Erro ao converter a data!");
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dia, mes, ano);
    }
}