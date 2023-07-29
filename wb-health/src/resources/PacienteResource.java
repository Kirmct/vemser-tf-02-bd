package resources;

import model.Hospital;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import repository.PacienteRepository;

public class PacienteResource {
    private PacienteRepository pacienteRepository = new PacienteRepository();

    public void inserir(Paciente paciente) {
        try {
            if (paciente.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            pacienteRepository.cadastrar(paciente);
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Unnexpected error: " +  e.getMessage());
        }
    }

//    public void listarTodos(Hospital hospital) {
//        pacienteRepository.listarTodos(hospital);
//    }
//
//    public void listarPeloId(Hospital hospital, Integer id) {
//        pacienteRepository.listarPeloId(hospital, id);
//    }
//
//    public void alterarPeloId(Hospital hospital, Integer id, Paciente pacienteAtualizado){
//        pacienteRepository.alterarPeloId(hospital, id, pacienteAtualizado);
//    }
//
//    public void deletarPeloId(Hospital hospital, Integer id){
//        pacienteRepository.deletarPeloId(hospital, id);
//    }
//
//    public Paciente buscarId(Hospital hospital, Integer id){
//        return pacienteRepository.buscarId(hospital.getPacientes(), id);
//    }
}
