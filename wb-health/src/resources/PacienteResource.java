package resources;

import model.Hospital;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import repository.PacienteRepository;
import util.CoresMenu;

import java.sql.Connection;
import java.util.List;

public class PacienteResource {
    private final PacienteRepository pacienteRepository = new PacienteRepository();

    public void inserir(Paciente paciente) {
        try {
            String cpf = paciente.getCpf().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos do CPF
            if (cpf.length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            paciente.setCpf(cpf);

            String cep = paciente.getCep().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos do CEP
            if (cep.length() != 8) {
                throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
            }
            paciente.setCep(cep); // Atualiza o CEP no objeto Paciente com o valor formatado
            pacienteRepository.cadastrar(paciente);

        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Unnexpected error: " +  e.getMessage());
        }
    }

    public boolean buscarCpf(Paciente paciente){
        return pacienteRepository.buscarCpf(paciente);
    }

    public void listarTodos() {
        try {
            List<Paciente> list = pacienteRepository.listarTodos();
            list.forEach(System.out::println);
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void listarPeloId(Integer id) throws BancoDeDadosException {
        Paciente paciente = pacienteRepository.listarPeloId(id);
        System.out.println(paciente);
    }

    public void alterarPeloId(Integer id, Paciente pacienteAtualizado) throws BancoDeDadosException {
        try {
            boolean consegueEditar = pacienteRepository.alterarPeloId(id, pacienteAtualizado);
            if (consegueEditar){

            }
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }

    }

    public void deletarPeloId(Integer id){
        try {
           boolean removeu =  pacienteRepository.deletarPeloId(id);
           if (removeu){
               System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
           }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }

    }

//    public Paciente buscarId(Hospital hospital, Integer id){
//        return pacienteRepository.buscarId(hospital.getPacientes(), id);
//    }
}
