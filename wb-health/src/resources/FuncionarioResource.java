package resources;

import model.Funcionario;
import model.Hospital;
import repository.FuncionarioRepository;


public class FuncionarioResource {
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public void inserir(Hospital hospital, Funcionario funcionario) {
        funcionarioRepository.cadastrar(hospital, funcionario);
    }

    public void listarTodos(Hospital hospital) {
        funcionarioRepository.listarTodos(hospital);
    }

    public void listarPeloId(Hospital hospital, Integer id) {
        funcionarioRepository.listarPeloId(hospital, id);
    }

    public void alterarPeloId(Hospital hospital, Integer id, Funcionario funcionarioAtualizado){
        funcionarioRepository.alterarPeloId(hospital, id, funcionarioAtualizado);
    }

    public void deletarPeloId(Hospital hospital, Integer id){
        funcionarioRepository.deletarPeloId(hospital, id);
    }
}
