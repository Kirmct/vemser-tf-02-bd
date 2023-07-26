package repository;

import model.Cadastro;
import model.Hospital;
import model.Paciente;
import model.exceptions.IdException;

import java.util.List;

public class PacienteRepository implements Cadastro<Paciente> {

    @Override
    public void cadastrar(Hospital hospital, Paciente paciente) {
        hospital.getPacientes().add(paciente);
    }

    @Override
    public void listarTodos(Hospital hospital) {
        hospital.getPacientes().forEach(System.out::println);
    }

    @Override
    public void listarPeloId(Hospital hospital, Integer id) {
        Paciente paciente = buscarId(hospital.getPacientes(), id);
        if (paciente == null){
            throw new IdException("Id não encontrado!");
        }
        System.out.println(paciente);
    }

    @Override
    public void alterarPeloId(Hospital hospital, Integer id, Paciente pacienteAtualizado) {
        Paciente paciente = buscarId(hospital.getPacientes(), id);
        if (paciente == null){
            throw new IdException("Id não encontrado!");
        }
        this.setarAtributos(paciente, pacienteAtualizado);
    }

    @Override
    public void deletarPeloId(Hospital hospital, Integer id) {
        boolean removeu = hospital.getPacientes().removeIf(paciente -> paciente.getId().equals(id));
        if (!removeu){
            throw new IdException("Id não encontrado!");
        }
    }

    @Override
    public void setarAtributos(Paciente paciente, Paciente pacienteAtualizado) {
        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setCep(pacienteAtualizado.getCep());
        paciente.setNome(pacienteAtualizado.getNome());
    }

    @Override
    public Paciente buscarId(List<Paciente> pacientes, Integer id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
}
