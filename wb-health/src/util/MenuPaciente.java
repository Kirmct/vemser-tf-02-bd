package util;

import model.Hospital;
import model.Paciente;
import resources.PacienteResource;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPaciente {
    private static final PacienteResource pacienteResource = new PacienteResource();

    public static void listar(){
        try {
            System.out.println("\n---------- Lista de pacientes ----------");
            pacienteResource.listarTodos();
        }catch (RuntimeException e){
//            System.out.println("Erro ao listar todos" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void inserir(Scanner sc){
        try {
            Paciente paciente;
            System.out.println("\n---------- Entre com os dados ----------");
//            System.out.print("Entre com o nome: ");
//            String nome = sc.nextLine();
//            System.out.print("Entre com o CEP: ");
//            String cep = sc.nextLine();
//            System.out.print("Entre com a data de nascimento(dd-mm-yyyy): ");
//            String dataNascimento = sc.nextLine();
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();


            paciente = new Paciente("kirmct", "55555555", "21-11-1998", cpf, 2000.00, 1);

            boolean cpfExists = pacienteResource.buscarCpf(paciente);
            if (cpfExists){
                throw new RuntimeException("CPF já existe em tabela!");
            }
            pacienteResource.inserir(paciente);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (RuntimeException e){
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }
//    public static void listarPeloID(Hospital hospital, Scanner sc) {
//        try {
//            System.out.println("\n---------- Entre com os dados ----------");
//            System.out.print("ID do paciente que deseja buscar: ");
//            Integer id = Integer.parseInt(sc.nextLine());
//            pacienteResource.listarPeloId(hospital, id);
//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
//        }catch (InputMismatchException e){
//            System.err.println("Input Inválido! ");
//        }
//        catch (RuntimeException e) {
//            System.err.println("Ocorreu um erro! " + e.getMessage());
//        }
//    }
//
//    public static void alterarPeloId(Hospital hospital, Scanner sc){
//        try {
//            System.out.println("\n---------- Entre com os dados ----------");
//            System.out.print("ID do paciente que deseja alterar: ");
//            Integer id = Integer.parseInt(sc.nextLine());
//            System.out.print("Novo nome do paciente: ");
//            String nome = sc.nextLine();
//            System.out.print("CPF do paciente: ");
//            String cpf = sc.nextLine();
//            System.out.print("Novo CEP do paciente: ");
//            String cep = sc.nextLine();
//
//            pacienteResource.alterarPeloId(hospital, id, new Paciente(nome, cep, cpf));
//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
//        }catch (InputMismatchException e){
//            System.err.println("Input Inválido! ");
//        }
//        catch (RuntimeException e) {
//            System.err.println("Ocorreu um erro! " + e.getMessage());
//        }
//    }
//
//    public static void deletarPeloId(Hospital hospital, Scanner sc) {
//        try {
//            System.out.println("\n---------- Entre com os dados ----------");
//            System.out.print("ID do paciente que deseja deletar: ");
//            Integer id = Integer.parseInt(sc.nextLine());
//            pacienteResource.deletarPeloId(hospital, id);
//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
//        }catch (InputMismatchException e){
//            System.err.println("Input Inválido! ");
//        }
//        catch (RuntimeException e) {
//            System.err.println("Ocorreu um erro! " + e.getMessage());
//        }
//    }

}
