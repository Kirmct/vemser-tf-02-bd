package util;

import model.Atendimento;
import model.Hospital;
import model.Medico;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;
import service.AtendimentoService;
import service.MedicoService;
import service.PacienteService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAtendimento {
    private static AtendimentoService atendimentoService = new AtendimentoService();
    private static PacienteService pacienteService = new PacienteService();
    private static MedicoService medicoService = new MedicoService();


    public static void listar() throws BancoDeDadosException {
        try {
            System.out.println("\n---------- Lista de atendimentos ----------");
            atendimentoService.listarTodos();
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar todos" + e.getMessage());
        }
    }

    public static void inserir(Scanner sc) throws BancoDeDadosException {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o id do paciente: ");
            Integer idPaciente = Integer.parseInt(sc.nextLine());
            Paciente pacienteExiste = pacienteService.buscarId(idPaciente);
            if (pacienteExiste.getIdPaciente() == null) {
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            System.out.print("Entre com o id do médico: ");
            Integer idMedico = Integer.parseInt(sc.nextLine());
            Medico medicoExiste = medicoService.buscarId(idMedico);
            if (medicoExiste.getIdMedico() == null) {
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            System.out.print("Entre com a data: ");
            String dataDeAtendimento = sc.nextLine();
            System.out.print("Entre com o laudo: ");
            String laudo = sc.nextLine();
            System.out.println("Escolha qual o seu atendimento (1- CONSULTA, 2- CIRURGIA, 3- EXAME, 4- RETORNO, 5- TRIAGEM): ");
            Integer codigoAtendimento = Integer.parseInt(sc.nextLine());

            TipoDeAtendimento tipoDeAtendimento;

            if (codigoAtendimento >= 6 || codigoAtendimento <= 0) {
                System.err.println("Código de atendimento inválido");
                return;
            }

            Atendimento atendimento = new Atendimento(1, pacienteExiste.getIdPaciente(), medicoExiste.getIdMedico(), dataDeAtendimento, laudo, codigoAtendimento);
            atendimentoService.inserir(atendimento);
            System.out.println("valor atendimento: " + atendimento.getValorDoAtendimento());
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);


        } catch (RuntimeException e) {
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }

    public static void listarPeloID(Scanner sc) throws BancoDeDadosException {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do atendimento que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            Atendimento atendimento = atendimentoService.buscarId(id);
            if (atendimento.getIdAtendimento() == null) {
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            Paciente paciente = pacienteService.buscarId(atendimento.getIdPaciente());
            Medico medico = medicoService.buscarId(atendimento.getIdMedico());
            atendimentoService.listarPeloId(id);
            System.out.println(paciente);
            System.out.println(medico);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void alterarPeloId(Scanner sc) throws BancoDeDadosException {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o id do atendimento: ");
            Integer idAtendimento = Integer.parseInt(sc.nextLine());
            Atendimento atendimentoExiste = atendimentoService.buscarId(idAtendimento);
            if (atendimentoExiste.getIdAtendimento() == null) {
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            System.out.print("Entre com a data: ");
            String dataAtendimento = sc.nextLine();
            System.out.print("Entre com o laudo: ");
            String laudo = sc.nextLine();
            System.out.println("Escolha qual o seu atendimento (1- CONSULTA, 2- CIRURGIA, 3- EXAME, 4- RETORNO, 5- TRIAGEM): ");
            Integer codigoAtendimento = Integer.parseInt(sc.nextLine());

            if (codigoAtendimento >= 6 || codigoAtendimento <= 0) {
                System.err.println("Código de atendimento inválido");
                return;
            }

            Atendimento atendimento = new Atendimento(1, atendimentoExiste.getIdPaciente(), atendimentoExiste.getIdMedico(), dataAtendimento, laudo, codigoAtendimento);
            atendimento.setIdAtendimento(atendimentoExiste.getIdAtendimento());
            atendimentoService.alterarPeloId(idAtendimento, atendimento);

        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

    public static void deletarPeloId(Scanner sc) throws BancoDeDadosException {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do atendimento que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            Atendimento atendimentoExiste = atendimentoService.buscarId(id);

            if (atendimentoExiste.getIdPaciente() == null){
                throw new IdException("Id não existe em nosso banco de dados!");
            }
            atendimentoService.deletarPeloId(id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

}
