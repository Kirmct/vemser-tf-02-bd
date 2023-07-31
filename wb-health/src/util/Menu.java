package util;

public class Menu {

    public static void listarMenuGeral(){
        System.out.println(CoresMenu.AMARELO_BOLD + "---------- ESCOLHA UMA OPÇÃO ----------"
        + "\n1 - Seção de Médicos"
        + "\n2 - Seção de Paciente"
        + "\n3 - Seção de Funcionário"
        + "\n4 - Seção de Atendimento"
        + "\n0 - Sair" + CoresMenu.RESET);
    }

    public static void listarSecao(int opcao){
        String secao = switch (opcao) {
            case 1 -> "médicos";
            case 2 -> "pacientes";
            case 3 -> "funcionários";
            case 4 -> "atendimentos";
            default -> throw new IllegalStateException("Valor inexperado: " + opcao);
        };

        String cor = switch (opcao) {
            case 1 -> CoresMenu.ROSA_BOLD;
            case 2 -> CoresMenu.AZUL_BOLD;
            case 3 -> CoresMenu.ROSA_BOLD;
            case 4 -> CoresMenu.AZUL_BOLD;
            default -> throw new IllegalStateException("Valor inexperado: " + opcao);
        };


        System.out.println(cor + "\n---------- SEÇÃO DE " + secao.toUpperCase() + " ----------"
        + "\n1 - Listar - " + secao
        + "\n2 - Inserir - " + secao
        + "\n3 - Buscar por Id - " + secao
        + "\n4 - Editar por Id - " + secao
        + "\n5 - Deletar por Id - " +secao
        + "\n0 - Voltar" + CoresMenu.RESET);



    }

}
