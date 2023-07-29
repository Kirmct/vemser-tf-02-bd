package model;

import model.exceptions.BancoDeDadosException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Cadastro <Key, T> {
    public void cadastrar(T entidade) throws BancoDeDadosException;

    public List<T> listarTodos() throws BancoDeDadosException;

    public T listarPeloId(Key id) throws BancoDeDadosException;

    public boolean alterarPeloId(Key id, T entidadeAtualizada) throws BancoDeDadosException;

    public boolean deletarPeloId(Key id) throws BancoDeDadosException;


    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException;

}
