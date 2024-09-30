// Felipe Souza Magalhães Sant'Anna / 202465148A
// Gabriel de Oliveira Vieira / 202265029A
// Isabela Salvador Romão / 202165065AB
// Maria Luiza Dornelas Corrêa / 201665194C

package model;

public class Sessao {
    private static boolean Admin = false;

    public static boolean Admin() {
        return Admin;
    }

    public static void setAdmin(boolean admin) {
        Admin = admin;
    }
}
