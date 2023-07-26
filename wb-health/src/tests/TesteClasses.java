package tests;

import model.*;
import util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TesteClasses {
    public static void main(String[] args) {

        System.out.println(" -- Teste Classe Funcionario: -- ");
        Funcionario f1 = new Funcionario("Lucas Torres", "58401-698", "104.332.454-43", 1350.0);
        System.out.println(f1.toString());

        System.out.println("\n -- Teste Classe Medico: -- ");
        Medico m1 = new Medico("Drauzio Varella", "084126-320", "15648-0", 30000.00);
        System.out.println(m1.toString());

        System.out.println("\n -- Teste Classe Paciente: -- ");
        Paciente p1 = new Paciente("Haroldo Bauer", "084126-320", "111.111.111-11");
        System.out.println(p1.toString());

        System.out.println("\n -- Teste Classe Atendimento: -- ");
        Atendimento a1 = new Atendimento(p1, m1, "21/07/2023", "virose", TipoDeAtendimento.EXAME);
        System.out.println(a1.toString());

        ArrayList<Medico> medicos = new ArrayList<>();
        medicos.add(m1);
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(p1);
        ArrayList<Atendimento> atendimentos = new ArrayList<>();
        atendimentos.add(a1);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(f1);

        System.out.println();

        Hospital hospital = new Hospital("cnpj", "WB Health", medicos, pacientes, atendimentos, funcionarios);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                Integer opcao = -1;

                Menu.listarMenuGeral();
                opcao = Integer.parseInt(sc.nextLine());
                if (opcao == 0) {
                    System.out.println("Obrigado por usar o WB Health, o seu serviço de saúde!");
                    return;
                }
                while (true) {
                    int selecao = -1;
                    Menu.listarSecao(hospital, opcao);
                    selecao = Integer.parseInt(sc.nextLine());
                    if (selecao == 0) {

                        break;
                    }
                    if (opcao == 1) {
                        switch (selecao) {
                            case 1 -> MenuMedico.listar(hospital);
                            case 2 -> MenuMedico.inserir(sc, hospital);
                            case 3 -> MenuMedico.listarPeloID(hospital, sc);
                            case 4 -> MenuMedico.alterarPeloId(hospital, sc);
                            case 5 -> MenuMedico.deletarPeloId(hospital, sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 2) {
                        switch (selecao) {
                            case 1 -> MenuPaciente.listar(hospital);
                            case 2 -> MenuPaciente.inserir(sc, hospital);
                            case 3 -> MenuPaciente.listarPeloID(hospital, sc);
                            case 4 -> MenuPaciente.alterarPeloId(hospital, sc);
                            case 5 -> MenuPaciente.deletarPeloId(hospital, sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 3) {
                        switch (selecao) {
                            case 1 -> MenuFuncionario.listar(hospital);
                            case 2 -> MenuFuncionario.inserir(sc, hospital);
                            case 3 -> MenuFuncionario.listarPeloID(hospital, sc);
                            case 4 -> MenuFuncionario.alterarPeloId(hospital, sc);
                            case 5 -> MenuFuncionario.deletarPeloId(hospital, sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }else if (opcao == 4) {
                        switch (selecao) {
                            case 1 -> MenuAtendimento.listar(hospital);
                            case 2 -> MenuAtendimento.inserir(sc, hospital);
                            case 3 -> MenuAtendimento.listarPeloID(hospital, sc);
                            case 4 -> MenuAtendimento.alterarPeloId(hospital, sc);
                            case 5 -> MenuAtendimento.deletarPeloId(hospital, sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 5) {
                       switch (selecao) {
                           default -> {
                               System.out.println("Selecione uma das opções!!");
                           }
                       }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada Inválida! " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Ocorreu um erro! " + e.getMessage());
            }
        }
    }
}

