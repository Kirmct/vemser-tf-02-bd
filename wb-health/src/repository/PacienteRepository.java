package repository;

import model.Cadastro;
import model.Hospital;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.exceptions.IdException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PacienteRepository implements Cadastro<Integer, Paciente> {


    @Override
    public void cadastrar(Paciente paciente) throws BancoDeDadosException{
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoPessoaId = this.getProximoId(con, "seq_pessoa.nextval");
            paciente.setIdPessoa(proximoPessoaId);

            String sqlPessoa = "INSERT INTO Pessoa\n" +
                    "(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stPesssoa = con.prepareStatement(sqlPessoa);

            stPesssoa.setInt(1, paciente.getIdPessoa());
            stPesssoa.setString(2, paciente.getNome());
            stPesssoa.setString(3, paciente.getCep());
            stPesssoa.setDate(4, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stPesssoa.setString(5, paciente.getCpf());
            stPesssoa.setDouble(6, paciente.getSalarioMensal());


            int pessoasInseridas = stPesssoa.executeUpdate();
            System.out.println("Pessoas inseridas: " + pessoasInseridas);

            if (pessoasInseridas == 0) throw new SQLException("Ocorreu um erro ao inserir!");

            Integer proximoPacienteId = this.getProximoId(con, "seq_paciente.nextval");
            paciente.setIdPaciente(proximoPacienteId);

            String sql = "INSERT INTO Paciente\n" +
                    "(id_paciente, id_hospital, id_pessoa)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stPaciente = con.prepareStatement(sql);
            stPaciente.setInt(1, paciente.getIdPaciente());
            stPaciente.setInt(2, 1);
            stPaciente.setInt(3, paciente.getIdPessoa());

            int res = stPaciente.executeUpdate();
            System.out.println("Pacientes inseridos:" + res);

        }catch (BancoDeDadosException e) {
            System.out.println("Erro ao acessar o banco de dados:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado:");
            e.printStackTrace();
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
    public List<Paciente> listarTodos() throws BancoDeDadosException {
        List<Paciente> pacientes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM PESSOA\n" +
                    "INNER JOIN PACIENTE\n" +
                    "ON PESSOA.ID_PESSOA = PACIENTE.ID_PESSOA";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Integer idPessoa = res.getInt("id_pessoa");
                String nome = res.getString("nome");
                String cep = res.getString("cep");
                LocalDate data =   res.getDate("data_nascimento").toLocalDate();
                String cpf = res.getString("cpf");
                Double salarioMensal = res.getDouble("salario_mensal");
                Integer idPaciente = res.getInt("id_paciente");
                Integer id_hospital = res.getInt("id_hospital");

                String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Paciente paciente = new Paciente(nome,cep, dataFormatada, cpf, salarioMensal, id_hospital);

                paciente.setIdPessoa(idPessoa);
                paciente.setIdPaciente(idPaciente);

                pacientes.add(paciente);
            }

        }catch (SQLException e) {
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
        return pacientes;
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

    @Override
    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException {
        try {
            String sql = "SELECT " + nextSequence + " mysequence from DUAL";
            System.out.println("SEQUENCE " + sql);
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public boolean buscarCpf(Paciente paciente){
        Connection con = null;
        boolean retorno = false;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM Pessoa WHERE cpf = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, paciente.getCpf());

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                retorno = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return retorno;

    }

}
