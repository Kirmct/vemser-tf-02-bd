package resources;

import model.Atendimento;
import model.Hospital;
import repository.AtendimentoRepository;


public class AtendimentoResource {
    private AtendimentoRepository atendimentoRepository = new AtendimentoRepository();

    public void inserir(Hospital hospital, Atendimento atendimento) {
        atendimentoRepository.cadastrar(hospital, atendimento);
    }

    public void listarTodos(Hospital hospital) {
        atendimentoRepository.listarTodos(hospital);
    }

    public void listarPeloId(Hospital hospital, Integer id) {
        atendimentoRepository.listarPeloId(hospital, id);
    }

    public void alterarPeloId(Hospital hospital, Integer id, Atendimento atendimentoAtualizado){
        atendimentoRepository.alterarPeloId(hospital, id, atendimentoAtualizado);
    }

    public void deletarPeloId(Hospital hospital, Integer id){
        atendimentoRepository.deletarPeloId(hospital, id);
    }
}
