package util;

import model.Funcionario;
import model.Hospital;
import resources.FuncionarioResource;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuFuncionario {
    private static FuncionarioResource funcionarioResource = new FuncionarioResource();

    public static void listar(Hospital hospital){
        try {
            System.out.println("\n---------- Lista de funcionários ----------");
            funcionarioResource.listarTodos(hospital);
        }catch (RuntimeException e){
            System.err.println("Erro ao listar todos" + e.getMessage());
        }
    }

    public static void inserir(Scanner sc, Hospital hospital){
        try {
            Funcionario funcionario;
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o nome: ");
            String nome = sc.nextLine();
            System.out.print("Entre com o CEP: ");
            String cep = sc.nextLine();
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Entre com o salário mensal: ");
            double salario = Double.parseDouble(sc.nextLine());

            funcionario = new Funcionario(nome, cep, cpf, salario);

            funcionarioResource.inserir(hospital, funcionario);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (RuntimeException e){
            System.err.println("Ocorreu um erro ao inserir: " + e.getMessage());
        }
    }
    public static void listarPeloID(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do funcionário que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            funcionarioResource.listarPeloId(hospital, id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void alterarPeloId(Hospital hospital, Scanner sc){
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do funcionário que deseja alterar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            System.out.print("Novo nome do funcionário: ");
            String nome = sc.nextLine();
            System.out.print("CPF do funcionário: ");
            String cpf = sc.nextLine();
            System.out.print("Novo CEP do funcionário: ");
            String cep = sc.nextLine();
            System.out.print("Entre com o salário mensal: ");
            double salario = Double.parseDouble(sc.nextLine());

            Funcionario funcionario = new Funcionario(nome, cep, cpf, salario);
            funcionarioResource.alterarPeloId(hospital, id, funcionario);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void deletarPeloId(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do funcionário que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            funcionarioResource.deletarPeloId(hospital, id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }
}
