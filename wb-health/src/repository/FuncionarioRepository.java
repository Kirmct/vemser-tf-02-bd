package repository;

import model.*;
import model.exceptions.IdException;

import java.util.List;

public class FuncionarioRepository implements Cadastro<Funcionario> {
    @Override
    public void cadastrar(Hospital hospital, Funcionario funcionario) {
        hospital.getFuncionarios().add(funcionario);
    }

    @Override
    public void listarTodos(Hospital hospital) {
        hospital.getFuncionarios().forEach(System.out::println);
    }

    @Override
    public void listarPeloId(Hospital hospital, Integer id) {
        Funcionario funcionario = buscarId(hospital.getFuncionarios(), id);
        if (funcionario == null) {
            throw new IdException("Id não encontrado!");
        }
        System.out.println(funcionario);
    }

    @Override
    public void alterarPeloId(Hospital hospital, Integer id, Funcionario funcionarioAtualizado) {
        Funcionario funcionario = buscarId(hospital.getFuncionarios(), id);
        if (funcionario == null) {
            throw new IdException("Id não encontrado!");
        }
        this.setarAtributos(funcionario, funcionarioAtualizado);
    }

    @Override
    public void deletarPeloId(Hospital hospital, Integer id) {
        boolean removeu = hospital.getFuncionarios().removeIf(funcionario -> funcionario.getId().equals(id));
        if (!removeu) {
            throw new IdException("Id não encontrado!");
        }
    }

    @Override
    public void setarAtributos(Funcionario funcionario, Funcionario funcionarioAtualizado) {
        funcionario.setCpf(funcionarioAtualizado.getCpf());
        funcionario.setSalarioMensal(funcionarioAtualizado.getSalarioMensal());
        funcionario.setCep(funcionarioAtualizado.getCep());
        funcionario.setNome(funcionarioAtualizado.getNome());
    }

    @Override
    public Funcionario buscarId(List<Funcionario> funcionarios, Integer id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
                return funcionario;
            }
        }
        return null;
    }
}
