package repository;

import model.Cadastro;
import model.Hospital;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;

import java.sql.*;
import java.util.List;

public class PacienteRepository implements Cadastro<Integer, Paciente> {


    @Override
    public void cadastrar(Paciente paciente) throws BancoDeDadosException{
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

//            Integer proximoId = this.getProximoId(con);
//            paciente.setId(proximoId);

            String sqlPessoa = "INSERT INTO Pessoa\n" +
                    "(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)\n" +
                    "VALUES(seq_pessoa.nextval, ?, ?, ?, ?, ?)\n";

            PreparedStatement stPesssoa = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            stPesssoa.setString(1, paciente.getNome());
            stPesssoa.setString(2, paciente.getCep());
            stPesssoa.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stPesssoa.setString(4, paciente.getCpf());
            stPesssoa.setDouble(5, paciente.getSalarioMensal());
            stPesssoa.executeUpdate();

            int pessoaId = 0;

            try (ResultSet generatedKeys = stPesssoa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pessoaId = generatedKeys.getInt(1);
                    System.out.println(pessoaId);
                } else {
                    throw new IdException("Falha ao obter o ID gerado da tabela Pessoa.");
                }
            }
            try {
                String sql = "INSERT INTO Paciente\n" +
                        "(id_hospital, id_pessoa)\n" +
                        "VALUES(SEQ_PACIENTE.nextval, ?, ?)\n";

                PreparedStatement stPaciente = con.prepareStatement(sql);
                stPaciente.setInt(1, 1);
                stPaciente.setInt(2, pessoaId);

                int res = stPaciente.executeUpdate();
                System.out.println("adicionarContato.res=" + res);
            }catch (SQLException e){
                System.out.println("Erro cadastras paciente: ");
                e.printStackTrace();
            }



        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void listarTodos() throws BancoDeDadosException {

    }

    @Override
    public void listarPeloId(Integer id) throws BancoDeDadosException {

    }

    @Override
    public void alterarPeloId(Integer id, Paciente entidadeAtualizada) throws BancoDeDadosException {

    }

    @Override
    public void deletarPeloId(Integer id) throws BancoDeDadosException {

    }

    @Override
    public void setarAtributos(Paciente entidade, Paciente entidadeAtualizada) throws BancoDeDadosException {

    }

    @Override
    public Paciente buscarId(Integer id) throws BancoDeDadosException {
        return null;
    }

//    @Override
//    public Integer getProximoId(Connection connection) throws SQLException {
//        try {
//            String sql = "SELECT seq_paciente.nextval mysequence from DUAL";
//            Statement stmt = connection.createStatement();
//            ResultSet res = stmt.executeQuery(sql);
//
//            if (res.next()) {
//                return res.getInt("mysequence");
//            }
//
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        }
//    }
}
