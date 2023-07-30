package tests;
import util.MenuMedico;
import java.util.Scanner;

public class MedicoTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuMedico.deletarPeloId(sc);
        MenuMedico.listar();

        MenuMedico.alterarPeloId(sc);

        System.out.println("Insira um id: ");
        MenuMedico.listarPeloID(sc);

        System.out.println("Insira um medico: ");
        MenuMedico.inserir(sc);
    }
}
