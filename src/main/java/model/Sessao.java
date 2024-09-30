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
