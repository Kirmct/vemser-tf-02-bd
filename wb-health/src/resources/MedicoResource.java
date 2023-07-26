package resources;

import model.Hospital;
import model.Medico;
import repository.MedicoRepository;

public class MedicoResource {

    private MedicoRepository medicoRepository = new MedicoRepository();

    public void inserir(Hospital hospital, Medico medico) {
        medicoRepository.cadastrar(hospital, medico);
    }

    public void listarTodos(Hospital hospital) {
        medicoRepository.listarTodos(hospital);
    }

    public void listarPeloId(Hospital hospital, Integer id) {
        medicoRepository.listarPeloId(hospital, id);
    }

    public void alterarPeloId(Hospital hospital, Integer id, Medico medicoAtualizado){
        medicoRepository.alterarPeloId(hospital, id, medicoAtualizado);
    }

    public void deletarPeloId(Hospital hospital, Integer id){
        medicoRepository.deletarPeloId(hospital, id);
    }

    public Medico buscarId(Hospital hospital, Integer id){
        return medicoRepository.buscarId(hospital.getMedicos(), id);
    }
}
