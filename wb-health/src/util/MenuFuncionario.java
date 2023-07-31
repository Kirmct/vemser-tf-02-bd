package util;

import model.Funcionario;

import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;
import service.FuncionarioService;

import java.util.InputMismatchException;
import java.util.Scanner;



public class MenuFuncionario {
    private static final FuncionarioService funcionarioService = new FuncionarioService();

    public static void listar(){
        try {
            System.out.println("\n---------- Lista de funcionarios ----------");
            funcionarioService.listarTodos();
        }catch (RuntimeException e){
            System.out.println("Erro ao listar todos" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void inserir(Scanner sc){
        try {
            Funcionario funcionario;
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o nome: ");
            String nome = sc.nextLine();
            System.out.print("Entre com o CEP: ");
            String cep = sc.nextLine();
            System.out.print("Entre com a data de nascimento(dd-mm-yyyy): ");
            String dataNascimento = sc.nextLine();
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Entre com o Salario Mensal: ");
            Double salarioMensal = Double.parseDouble(sc.nextLine());

            funcionario = new Funcionario(nome, cep, dataNascimento, cpf, salarioMensal, 1);

            boolean cpfExists = funcionarioService.buscarCpf(funcionario);

            if (cpfExists){
                throw new RuntimeException("CPF já existe em tabela!");
            }
            funcionarioService.inserir(funcionario);

        }catch (RuntimeException e){
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }
    public static void listarPeloID(Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do funcionario que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Funcionario funcionarioExiste = funcionarioService.buscarId(id);
            if (funcionarioExiste.getIdFuncionario() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            funcionarioService.listarPeloId(id);
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
            System.out.print("ID do funcionario que deseja alterar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Funcionario funcionarioExiste = funcionarioService.buscarId(id);
            if (funcionarioExiste.getIdFuncionario() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            System.out.print("Novo nome do funcionario: ");
            String nome = sc.nextLine();

            System.out.print("Novo CEP do funcionario: ");
            String cep = sc.nextLine();

            System.out.print("Novo data nascimento do funcionario(dd-MM-yyyy): ");
            String dataNascimento = sc.nextLine();

            System.out.print("CPF do funcionario: ");
            String cpf = sc.nextLine();

            System.out.print("Novo salário: ");
            Double salario = Double.parseDouble(sc.nextLine());


            Funcionario funcionario;
            funcionario = new Funcionario(nome, cep, dataNascimento, cpf, salario, 1);

            funcionarioService.alterarPeloId(id, funcionario);
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
            System.out.print("ID do funcionario que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Funcionario funcionarioExiste = funcionarioService.buscarId(id);
            if (funcionarioExiste.getIdFuncionario() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            funcionarioService.deletarPeloId(id);

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