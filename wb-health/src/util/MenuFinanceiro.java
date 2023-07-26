package util;

import model.Atendimento;
import model.Funcionario;
import model.Hospital;
import model.Medico;

public class MenuFinanceiro {
    public static void listar(Hospital hospital) {
        Double folhaMensal = 0.0;
        Double folhaAnual = 0.0;
        Double ganhoAtendimentos = 0.0;

        for (Funcionario funcionario : hospital.getFuncionarios()) {
            folhaMensal += funcionario.calcularSalarioMensal();
            folhaAnual += funcionario.calcularSalarioAnual();
        }
        for (Medico medico : hospital.getMedicos()) {
            folhaMensal += medico.calcularSalarioMensal();
            folhaAnual += medico.calcularSalarioAnual();
        }

        for (Atendimento atendimento : hospital.getAtendimentos()) {
            ganhoAtendimentos += atendimento.getValor();
        }

        System.out.println(CoresMenu.VERDE_BOLD +"\n---------- SEÇÃO FINANCEIRA ----------"
        + "\nFolha Mensal: R$" + String.format("%.2f", folhaMensal)
        + "\nFolha Anual: R$" + String.format("%.2f", folhaAnual)
        + "\nGanhos Totais: R$" + String.format("%.2f", ganhoAtendimentos)
        + "\n\n0 - Voltar" + CoresMenu.RESET);
    }

}
