package util;

import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;
import service.PacienteService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPaciente {
    private static final PacienteService pacienteService = new PacienteService();

    public static void listar(){
        try {
            System.out.println("\n---------- Lista de pacientes ----------");
            pacienteService.listarTodos();
        }catch (RuntimeException e){
            System.out.println("Erro ao listar todos" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void inserir(Scanner sc){
        try {
            Paciente paciente;
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o nome: ");
            String nome = sc.nextLine();
            System.out.print("Entre com o CEP: ");
            String cep = sc.nextLine();
            System.out.print("Entre com a data de nascimento(dd-mm-yyyy): ");
            String dataNascimento = sc.nextLine();
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();

            paciente = new Paciente(nome, cep, dataNascimento, cpf, 0.00, 1);

            boolean cpfExists = pacienteService.buscarCpf(paciente);

            if (cpfExists){
                throw new RuntimeException("CPF já existe em tabela!");
            }
            pacienteService.inserir(paciente);

        }catch (RuntimeException e){
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }
    public static void listarPeloID(Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do paciente que deseja buscar: ");


            Integer id = Integer.parseInt(sc.nextLine());
            Paciente pacienteExiste = pacienteService.buscarId(id);
            if (pacienteExiste == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            pacienteService.listarPeloId(id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarPeloId(Scanner sc){
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do paciente que deseja alterar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Paciente pacienteExiste = pacienteService.buscarId(id);
            if (pacienteExiste == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            System.out.print("Novo nome do paciente: ");
            String nome = sc.nextLine();

            System.out.print("Novo CEP do paciente: ");
            String cep = sc.nextLine();

            System.out.print("Novo data nascimento do paciente(dd-MM-yyyy): ");
            String dataNascimento = sc.nextLine();

            System.out.print("CPF do paciente: ");
            String cpf = sc.nextLine();

            System.out.print("Novo salário: ");
            Double salario = Double.parseDouble(sc.nextLine());


            Paciente paciente;
            paciente = new Paciente(nome, cep, dataNascimento, cpf, salario, 1);

            pacienteService.alterarPeloId(id, paciente);
        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
            e.printStackTrace();
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletarPeloId(Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do paciente que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Paciente pacienteExiste = pacienteService.buscarId(id);

            if (pacienteExiste == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            pacienteService.deletarPeloId(id);

        }catch (InputMismatchException e){
            System.err.println("Input Inválido! ");
        }
        catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }
    }

}
