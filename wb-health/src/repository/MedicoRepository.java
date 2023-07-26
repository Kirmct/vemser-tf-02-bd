package repository;

import model.Cadastro;
import model.Hospital;
import model.Medico;
import model.exceptions.IdException;

import java.util.List;

public class MedicoRepository implements Cadastro<Medico> {


    @Override
    public void cadastrar(Hospital hospital, Medico medico) {
        hospital.getMedicos().add(medico);
    }

    @Override
    public void listarTodos(Hospital hospital) {
        hospital.getMedicos().forEach(System.out::println);
    }

    @Override
    public void listarPeloId(Hospital hospital, Integer id) {
        Medico medico = buscarId(hospital.getMedicos(), id);
        if (medico == null){
            throw new IdException("Id não encontrado!");
        }
        System.out.println(medico);
    }

    @Override
    public void alterarPeloId(Hospital hospital, Integer id, Medico medicoAtualizado) {
        Medico medico = buscarId(hospital.getMedicos(), id);
        if (medico == null){
            throw new IdException("Id não encontrado!");
        }
        this.setarAtributos(medico, medicoAtualizado);

    }

    @Override
    public void deletarPeloId(Hospital hospital, Integer id) {
        boolean removeu =  hospital.getMedicos().removeIf(medico -> medico.getId().equals(id));
        if (!removeu){
            throw new IdException("Id não encontrado!");
        }
    }

    @Override
    public void setarAtributos(Medico medico, Medico medicoAtualizado) {
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setSalarioMensal(medicoAtualizado.getSalarioMensal());
        medico.setCep(medicoAtualizado.getCep());
        medico.setNome(medicoAtualizado.getNome());

    }

    @Override
    public Medico buscarId(List<Medico> medicos, Integer id) {
        for (Medico medico : medicos) {
            if (medico.getId().equals(id)) {
                return medico;
            }
        }
        return null;
    }
}
