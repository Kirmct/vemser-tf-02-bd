import model.*;
import model.exceptions.BancoDeDadosException;
import util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println(CoresMenu.CIANO_BOLD +  "\n" +
                "██╗    ██╗██████╗     ██╗  ██╗███████╗ █████╗ ██╗  ████████╗██╗  ██╗\n" +
                "██║    ██║██╔══██╗    ██║  ██║██╔════╝██╔══██╗██║  ╚══██╔══╝██║  ██║\n" +
                "██║ █╗ ██║██████╔╝    ███████║█████╗  ███████║██║     ██║   ███████║\n" +
                "██║███╗██║██╔══██╗    ██╔══██║██╔══╝  ██╔══██║██║     ██║   ██╔══██║\n" +
                "╚███╔███╔╝██████╔╝    ██║  ██║███████╗██║  ██║███████╗██║   ██║  ██║\n" +
                " ╚══╝╚══╝ ╚═════╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝  ╚═╝\n" +
                "                                                                    " + CoresMenu.RESET);
        System.out.println(CoresMenu.CIANO_BOLD + "++++++++++++ Bem-Vindo ao Sistema de Gestão da WB Health ++++++++++++\n" + CoresMenu.RESET);

        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                Integer opcao = -1;


                Menu.listarMenuGeral();
                opcao = Integer.parseInt(sc.nextLine());
                if (opcao == 0) {
                    System.out.println(CoresMenu.CIANO_BOLD + "\n++++ Obrigado por usar o sistema da WB Health, o seu serviço de gestão em saúde! ++++" + CoresMenu.RESET);
                    return;
                }
                while (true) {
                    int selecao = -1;
                    Menu.listarSecao(opcao);
                    selecao = Integer.parseInt(sc.nextLine());
                    if (selecao == 0) {

                        break;
                    }
                    if (opcao == 1) {
                        switch (selecao) {
                            case 1 -> MenuMedico.listar();
                            case 2 -> MenuMedico.inserir(sc);
                            case 3 -> MenuMedico.listarPeloID(sc);
                            case 4 -> MenuMedico.alterarPeloId( sc);
                            case 5 -> MenuMedico.deletarPeloId(sc);
                            default -> {
                                System.err.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 2) {
                        switch (selecao) {
                            case 1 -> MenuPaciente.listar();
                            case 2 -> MenuPaciente.inserir(sc);
                            case 3 -> MenuPaciente.listarPeloID( sc);
                            case 4 -> MenuPaciente.alterarPeloId( sc);
                            case 5 -> MenuPaciente.deletarPeloId( sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 3) {
                        switch (selecao) {
                            case 1 -> MenuFuncionario.listar();
                            case 2 -> MenuFuncionario.inserir(sc);
                            case 3 -> MenuFuncionario.listarPeloID( sc);
                            case 4 -> MenuFuncionario.alterarPeloId( sc);
                            case 5 -> MenuFuncionario.deletarPeloId( sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }else if (opcao == 4) {
                        switch (selecao) {
                            case 1 -> MenuAtendimento.listar();
                            case 2 -> MenuAtendimento.inserir(sc);
                            case 3 -> MenuAtendimento.listarPeloID( sc);
                            case 4 -> MenuAtendimento.alterarPeloId( sc);
                            case 5 -> MenuAtendimento.deletarPeloId( sc);
                            default -> {
                                System.out.println("Selecione uma das opções!!");
                            }
                        }
                    }
                    else if (opcao == 5) {
                        switch (selecao) {
                            default -> {
                                System.err.println("Selecione uma das opções!!");
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada Inválida! ");
            }catch (RuntimeException e){
                System.err.println("Valor inexperado!");
            } catch (BancoDeDadosException e) {
                throw new RuntimeException(e);
            }
        }
    }
}