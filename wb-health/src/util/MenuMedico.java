package util;

import model.Hospital;
import model.Medico;
import resources.MedicoResource;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMedico {
    private static MedicoResource medicoResource = new MedicoResource();

    public static void listar(Hospital hospital) {
        try {
            System.out.println("\n---------- Lista de médicos ----------");
            medicoResource.listarTodos(hospital);
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar todos" + e.getMessage());
        }
    }

    public static void inserir(Scanner sc, Hospital hospital) {
        try {
            Medico medico;
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o nome: ");
            String nome = sc.nextLine();
            System.out.print("Entre com o CEP: ");
            String cep = sc.nextLine();
            System.out.print("Entre com o CRM: ");
            String crm = sc.nextLine();
            System.out.print("Entre com o salário mensal: ");
            double salario = Double.parseDouble(sc.nextLine());

            medico = new Medico(nome, cep, crm, salario);

            medicoResource.inserir(hospital, medico);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (RuntimeException e) {
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }

    public static void listarPeloID(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do médico que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            medicoResource.listarPeloId(hospital, id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void alterarPeloId(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do médico que deseja alterar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            System.out.print("Novo nome do médico: ");
            String nome = sc.nextLine();
            System.out.print("CRM do médico: ");
            String crm = sc.nextLine();
            System.out.print("Novo CEP do médico: ");
            String cep = sc.nextLine();
            System.out.print("Novo salário do médico: ");
            Double salario = Double.parseDouble(sc.nextLine());

            medicoResource.alterarPeloId(hospital, id, new Medico(nome, cep, crm, salario));
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void deletarPeloId(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do médico que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            medicoResource.deletarPeloId(hospital, id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

}
