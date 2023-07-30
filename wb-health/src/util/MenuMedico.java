package util;
import model.Atendimento;
import model.Medico;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;
import service.AtendimentoService;
import service.MedicoService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuMedico {
    private static final MedicoService medicoService= new MedicoService();
    private static final AtendimentoService atendimentoService = new AtendimentoService();
    public static void listar(){
        try {
            System.out.println("\n---------- Lista de medicos ----------");
            medicoService.listarTodos();
        }catch (RuntimeException e){
            System.out.println("Erro ao listar todos" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void inserir(Scanner sc) {
        try {
            Medico medico;
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o nome: ");
            String nome = sc.nextLine();
            System.out.print("Entre com o CEP: ");
            String cep = sc.nextLine();
            System.out.print("Entre com a data de nascimento(dd-mm-yyyy): ");
            String dataNascimento = sc.nextLine();
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Entre com o CRM: ");
            String crm = sc.nextLine();
            System.out.print("Entre com salario: ");
            Double salario = Double.valueOf(sc.nextLine());

            medico = new Medico(nome, cep, dataNascimento, cpf,salario , 1, crm);

            MedicoService medicoService = new MedicoService();

            boolean cpfExists = medicoService.buscarCpf(medico);

            if (cpfExists){
                throw new RuntimeException("CPF já existe em tabela!");
            }
            medicoService.inserir(medico);

        }catch (RuntimeException e){
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }

    public static void listarPeloID(Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do medico que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());

            Medico medicoExiste = medicoService.buscarId(id);
            if (medicoExiste.getIdMedico() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            medicoService.listarPeloId(id);
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
            System.out.print("ID do medico que deseja alterar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            Medico medicoExiste = medicoService.buscarId(id);
            if (medicoExiste.getIdMedico() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            System.out.print("Novo nome do medico: ");
            String nome = sc.nextLine();

            System.out.print("Novo CEP do medico: ");
            String cep = sc.nextLine();

            System.out.print("Novo data nascimento do medico(dd-MM-yyyy): ");
            String dataNascimento = sc.nextLine();

            System.out.print("CPF do medico: ");
            String cpf = sc.nextLine();

            System.out.print("Novo salário: ");
            Double salario = Double.parseDouble(sc.nextLine());

            System.out.print("Novo crm: ");
            String crm = sc.nextLine();

            Medico medico;
            medico = new Medico(nome, cep, dataNascimento, cpf, salario, 1, crm);

            medicoService.alterarPeloId(id, medico);
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
            System.out.print("ID do medico que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            Medico medicoExiste = medicoService.buscarId(id);
            if (medicoExiste.getIdMedico() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }

            List<Atendimento> atendimentos = atendimentoService.buscarTodos();
            for (Atendimento atendimento: atendimentos){
                if (atendimento.getIdMedico() == medicoExiste.getIdMedico()){
                    throw new IdException("Não podemos remover médicos associados a um atendimento!");
                }
            }

            medicoService.deletarPeloId(id);

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
