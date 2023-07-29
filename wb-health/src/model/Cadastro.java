package model;

import model.exceptions.BancoDeDadosException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Cadastro <Key, T> {
    public void cadastrar(T entidade) throws BancoDeDadosException;

    public List<T> listarTodos() throws BancoDeDadosException;

    public void listarPeloId(Key id) throws BancoDeDadosException;

    public void alterarPeloId(Key id, T entidadeAtualizada) throws BancoDeDadosException;

    public void deletarPeloId(Key id) throws BancoDeDadosException;;
    public void setarAtributos(T entidade, T entidadeAtualizada) throws BancoDeDadosException;

    public T buscarId(Key id) throws BancoDeDadosException;

    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException;

}
