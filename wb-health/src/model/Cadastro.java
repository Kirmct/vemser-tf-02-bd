package model;

import java.util.List;

public interface Cadastro <T> {
    public void cadastrar(Hospital hospital, T entidade);
    public void listarTodos(Hospital hospital);
    public void listarPeloId(Hospital hospital, Integer id);
    public void alterarPeloId(Hospital hospital, Integer id, T entidade);
    public void deletarPeloId(Hospital hospital, Integer id);
    public void setarAtributos(T entidade, T entidadeAtualizada);
    public T buscarId(List<T> entidade, Integer id);

}
