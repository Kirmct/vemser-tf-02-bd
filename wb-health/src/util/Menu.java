package util;

import model.Hospital;

public class Menu {

    public static void listarMenuGeral(){
        System.out.println(CoresMenu.AMARELO_BOLD + "---------- ESCOLHA UMA OPÇÃO ----------"
        + "\n1 - Seção de Médicos"
        + "\n2 - Seção de Paciente"
        + "\n3 - Seção de Funcionário"
        + "\n4 - Seção de Atendimento"
        + "\n5 - Seção do Financeiro"
        + "\n0 - Sair" + CoresMenu.RESET);
    }

    public static void listarSecao(Hospital hospital, int opcao){
        String secao = switch (opcao) {
            case 1 -> "médicos";
            case 2 -> "pacientes";
            case 3 -> "funcionários";
            case 4 -> "atendimentos";
            case 5 -> "financeiro";
            default -> throw new IllegalStateException("Valor inexperado: " + opcao);
        };

        String cor = switch (opcao) {
            case 1 -> CoresMenu.ROSA_BOLD;
            case 2 -> CoresMenu.AZUL_BOLD;
            case 3 -> CoresMenu.ROSA_BOLD;
            case 4 -> CoresMenu.AZUL_BOLD;
            case 5 -> CoresMenu.VERDE_BOLD;
            default -> throw new IllegalStateException("Valor inexperado: " + opcao);
        };

        if (opcao != 5) {
        System.out.println(cor + "\n---------- SEÇÃO DE " + secao.toUpperCase() + " ----------"
        + "\n1 - Listar - " + secao
        + "\n2 - Inserir - " + secao
        + "\n3 - Buscar por Id - " + secao
        + "\n4 - Editar por Id - " + secao
        + "\n5 - Deletar por Id - " +secao
        + "\n0 - Voltar" + CoresMenu.RESET);
        }

        else if (opcao == 5) {
            MenuFinanceiro.listar(hospital);
        }
    }

}
