package tests;

import util.MenuPaciente;

import java.util.Scanner;

public class TesteDb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println("Inserir paciente: ");
//        MenuPaciente.inserir(sc);

//        System.out.println("Listar pacientes: ");
//        MenuPaciente.listar();

//        System.out.println("Listar por id: ");
//        MenuPaciente.listarPeloID(sc);

//        System.out.println("Editar paciente por Id: ");
//        MenuPaciente.alterarPeloId(sc);

        System.out.println("Remover pessoa por id: ");
        MenuPaciente.deletarPeloId(sc);

        sc.close();
    }
}
