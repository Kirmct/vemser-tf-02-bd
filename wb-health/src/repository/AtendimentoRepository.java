package repository;

import model.Atendimento;
import model.Cadastro;
import model.Hospital;
import model.exceptions.IdException;

import java.util.List;

public class AtendimentoRepository implements Cadastro<Atendimento> {
    @Override
    public void cadastrar(Hospital hospital, Atendimento atendimento) {
        hospital.getAtendimentos().add(atendimento);
    }

    @Override
    public void listarTodos(Hospital hospital) {
       hospital.getAtendimentos().forEach(System.out::println);
    }

    @Override
    public void listarPeloId(Hospital hospital, Integer id) {
        Atendimento atendimento = buscarId(hospital.getAtendimentos(), id);
        if (atendimento == null){
            throw new IdException("Id não encontrado!");
        }
        System.out.println(atendimento);

    }

    @Override
    public void alterarPeloId(Hospital hospital, Integer id, Atendimento atendimentoAtualizado) {
        Atendimento atendimento = this.buscarId(hospital.getAtendimentos(), id);
        if (atendimento == null){
            throw new IdException("Id não encontrado!");
        }
        this.setarAtributos(atendimento, atendimentoAtualizado);
    }

    @Override
    public void deletarPeloId(Hospital hospital, Integer id) {
        boolean removeu = hospital.getAtendimentos().removeIf(atendimento -> atendimento.getId().equals(id));
        if (!removeu){
            throw new IdException("Id não encontrado!");
        }
    }

    @Override
    public void setarAtributos(Atendimento atendimento, Atendimento atendimentoAtualizado) {
        atendimento.setData(atendimentoAtualizado.getData());
        atendimento.setTipoDeAtendimento(atendimentoAtualizado.getTipoDeAtendimento());
        atendimento.setLaudo(atendimentoAtualizado.getLaudo());
        atendimento.calcularValorDoAtendimento(atendimentoAtualizado.getTipoDeAtendimento());
    }

    @Override
    public Atendimento buscarId(List<Atendimento> atendimentos, Integer id) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getId().equals(id)) {
                return atendimento;
            }
        }
        return null;
    }

}
