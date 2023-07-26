package util;

import model.Atendimento;
import model.Hospital;
import model.Medico;
import model.Paciente;
import resources.AtendimentoResource;
import resources.MedicoResource;
import resources.PacienteResource;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAtendimento {
    private static MedicoResource medicoResource = new MedicoResource();
    private static PacienteResource pacienteResource = new PacienteResource();

    private static AtendimentoResource atendimentoResource = new AtendimentoResource();

    public static void listar(Hospital hospital) {
        try {
            System.out.println("\n---------- Lista de atendimentos ----------");
            atendimentoResource.listarTodos(hospital);
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar todos" + e.getMessage());
        }
    }

    public static void inserir(Scanner sc, Hospital hospital) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("Entre com o id do paciente: ");
            Integer idPaciente = Integer.parseInt(sc.nextLine());
            System.out.print("Entre com o id do médico: ");
            Integer idMedico = Integer.parseInt(sc.nextLine());
            System.out.print("Entre com a data: ");
            String data = sc.nextLine();
            System.out.print("Entre com o laudo: ");
            String laudo = sc.nextLine();
            System.out.println("Escolha qual o seu atendimento (1- CONSULTA, 2- CIRURGIA, 3- EXAME, 4- RETORNO, 5- TRIAGEM): ");
            Integer codigoAtendimento = Integer.parseInt(sc.nextLine());

            TipoDeAtendimento tipoDeAtendimento;

            switch (codigoAtendimento) {
                case 1:
                    tipoDeAtendimento = TipoDeAtendimento.CONSULTA;
                    break;
                case 2:
                    tipoDeAtendimento = TipoDeAtendimento.CIRURGIA;
                    break;
                case 3:
                    tipoDeAtendimento = TipoDeAtendimento.EXAME;
                    break;
                case 4:
                    tipoDeAtendimento = TipoDeAtendimento.RETORNO;
                    break;
                case 5:
                    tipoDeAtendimento = TipoDeAtendimento.TRIAGEM;
                    break;
                default:
                    System.out.println("Número inválido. Estamos finalizando o atendimento.");
                    return;
            }

            Medico medico = medicoResource.buscarId(hospital, idMedico);
            Paciente paciente = pacienteResource.buscarId(hospital, idPaciente);

            if (medico == null || paciente == null) {
                System.out.println("Campo paciente ou médico inválido");
                return;
            } else {
                Atendimento atendimento = new Atendimento(paciente, medico, data, laudo, tipoDeAtendimento);
                atendimentoResource.inserir(hospital, atendimento);
                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }

        } catch (RuntimeException e) {
            System.out.println("Ocorreu ao inserir: " + e.getMessage());
        }
    }

    public static void listarPeloID(Hospital hospital, Scanner sc) {
        try {
            System.out.println("\n---------- Entre com os dados ----------");
            System.out.print("ID do atendimento que deseja buscar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            atendimentoResource.listarPeloId(hospital, id);
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
            System.out.print("Entre com o id do atendimento: ");
            Integer idAtendimento = Integer.parseInt(sc.nextLine());
            System.out.print("Entre com a data: ");
            String data = sc.nextLine();
            System.out.print("Entre com o laudo: ");
            String laudo = sc.nextLine();
            System.out.println("Escolha qual o seu atendimento (1- CONSULTA, 2- CIRURGIA, 3- EXAME, 4- RETORNO, 5- TRIAGEM): ");
            Integer codigoAtendimento = Integer.parseInt(sc.nextLine());

            TipoDeAtendimento tipoDeAtendimento;

            switch (codigoAtendimento) {
                case 1:
                    tipoDeAtendimento = TipoDeAtendimento.CONSULTA;
                    break;
                case 2:
                    tipoDeAtendimento = TipoDeAtendimento.CIRURGIA;
                    break;
                case 3:
                    tipoDeAtendimento = TipoDeAtendimento.EXAME;
                    break;
                case 4:
                    tipoDeAtendimento = TipoDeAtendimento.RETORNO;
                    break;
                case 5:
                    tipoDeAtendimento = TipoDeAtendimento.TRIAGEM;
                    break;
                default:
                    System.out.println("Número inválido. Estamos finalizando o atendimento.");
                    return;
            }

            Atendimento atendimento = new Atendimento(null, null, data, laudo, tipoDeAtendimento);
            atendimentoResource.alterarPeloId(hospital, idAtendimento, atendimento);
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
            System.out.print("ID do atendimento que deseja deletar: ");
            Integer id = Integer.parseInt(sc.nextLine());
            atendimentoResource.deletarPeloId(hospital, id);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
        } catch (InputMismatchException e) {
            System.err.println("Input Inválido! ");
        } catch (RuntimeException e) {
            System.err.println("Ocorreu um erro! " + e.getMessage());
        }
    }

}
