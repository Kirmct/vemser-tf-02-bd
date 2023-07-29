package tests;

import util.MenuPaciente;

import java.util.Scanner;

public class TesteDb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserir paciente: ");
        MenuPaciente.inserir(sc);
        sc.close();
    }
}
