package repository;

import model.Funcionario;
import model.exceptions.BancoDeDadosException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements Repositorio<Integer, Funcionario> {

    @Override
    public void cadastrar(Funcionario funcionario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoPessoaId = this.getProximoId(con, "seq_pessoa.nextval");
            funcionario.setIdPessoa(proximoPessoaId);
            System.out.println(proximoPessoaId);

            String sqlPessoa = "INSERT INTO Pessoa\n" +
                    "(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stPesssoa = con.prepareStatement(sqlPessoa);

            stPesssoa.setInt(1, funcionario.getIdPessoa());
            stPesssoa.setString(2, funcionario.getNome());
            stPesssoa.setString(3, funcionario.getCep());
            stPesssoa.setDate(4, java.sql.Date.valueOf(funcionario.getDataNascimento()));
            stPesssoa.setString(5, funcionario.getCpf());
            stPesssoa.setDouble(6, funcionario.getSalarioMensal());


            int pessoasInseridas = stPesssoa.executeUpdate();
            System.out.println("Pessoas inseridas: " + pessoasInseridas);

            if (pessoasInseridas == 0) throw new SQLException("Ocorreu um erro ao inserir!");

            Integer proximoFuncionarioId = this.getProximoId(con, "seq_funcionario.nextval");
            funcionario.setIdFuncionario(proximoFuncionarioId);

            String sql = "INSERT INTO Funcionario\n" +
                    "(id_funcionario, id_hospital, id_pessoa)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stFuncionario = con.prepareStatement(sql);
            stFuncionario.setInt(1, funcionario.getIdFuncionario());
            stFuncionario.setInt(2, 1);
            stFuncionario.setInt(3, funcionario.getIdPessoa());

            int res = stFuncionario.executeUpdate();
            System.out.println("Funcionarios inseridos:" + res);

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
    public List<Funcionario> listarTodos() throws BancoDeDadosException {
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM PESSOA\n" +
                    "INNER JOIN FUNCIONARIO\n" +
                    "ON PESSOA.ID_PESSOA = FUNCIONARIO.ID_PESSOA";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Integer idPessoa = res.getInt("id_pessoa");
                String nome = res.getString("nome");
                String cep = res.getString("cep");
                LocalDate data =   res.getDate("data_nascimento").toLocalDate();
                String cpf = res.getString("cpf");
                Double salarioMensal = res.getDouble("salario_mensal");
                Integer idFuncionario = res.getInt("id_funcionario");
                Integer id_hospital = res.getInt("id_hospital");

                String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Funcionario funcionario = new Funcionario(nome,cep, dataFormatada, cpf, salarioMensal, id_hospital);

                funcionario.setIdPessoa(idPessoa);
                funcionario.setIdFuncionario(idFuncionario);

                funcionarios.add(funcionario);
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
        return funcionarios;
    }

    @Override
    public Funcionario listarPeloId(Integer id) throws BancoDeDadosException {
        Funcionario funcionario = new Funcionario();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM FUNCIONARIO\n" +
                    "INNER JOIN PESSOA ON FUNCIONARIO.ID_FUNCIONARIO = ? AND PESSOA.ID_PESSOA = FUNCIONARIO.ID_PESSOA\n";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);


            ResultSet res = st.executeQuery();
            if (res.next()){
                Integer idPessoa = res.getInt("id_pessoa");
                String nome = res.getString("nome");
                String cep = res.getString("cep");
                LocalDate data =   res.getDate("data_nascimento").toLocalDate();
                String cpf = res.getString("cpf");
                Double salarioMensal = res.getDouble("salario_mensal");
                Integer idFuncionario = res.getInt("id_funcionario");
                Integer id_hospital = res.getInt("id_hospital");

                String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                funcionario = new Funcionario(nome,cep, dataFormatada, cpf, salarioMensal, id_hospital);

                funcionario.setIdPessoa(idPessoa);
                funcionario.setIdFuncionario(idFuncionario);
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
        return funcionario;
    }

    @Override
    public boolean alterarPeloId(Integer id, Funcionario funcionario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Funcionario funcionarioId = this.listarPeloId(id);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET \n");

            List<String> camposAtualizados = new ArrayList<>();
            if (funcionario != null) {
                if (funcionario.getNome() != null) {
                    camposAtualizados.add("nome = ?");
                }
                if (funcionario.getCep() != null) {
                    camposAtualizados.add("cep = ?");
                }
                if (funcionario.getDataNascimento() != null) {
                    camposAtualizados.add("data_nascimento = ?");
                }
                if (funcionario.getCpf() != null) {
                    camposAtualizados.add("cpf = ?");
                }
                if (funcionario.getSalarioMensal() != null) {
                    camposAtualizados.add("salario_mensal = ?");
                }
            }

            if (!camposAtualizados.isEmpty()) {
                sql.append(String.join(", ", camposAtualizados));
                sql.append(" WHERE id_pessoa = ?");

                PreparedStatement st = con.prepareStatement(sql.toString());

                int index = 1;
                if (funcionario != null) {
                    if (funcionario.getNome() != null) {
                        st.setString(index++, funcionario.getNome());
                    }
                    if (funcionario.getCep() != null) {
                        st.setString(index++, funcionario.getCep());
                    }
                    if (funcionario.getDataNascimento() != null) {
                        st.setDate(index++, java.sql.Date.valueOf(funcionario.getDataNascimento()));
                    }
                    if (funcionario.getCpf() != null) {
                        st.setString(index++, funcionario.getCpf());
                    }
                    if (funcionario.getSalarioMensal() != null) {
                        st.setDouble(index++, funcionario.getSalarioMensal());
                    }
                }

                st.setInt(index++, funcionarioId.getIdPessoa());

                int res = st.executeUpdate();
                System.out.println("Update: " + res);

                return res > 0;
            } else {
                System.out.println("Nenhum campo para atualizar.");
                return false;
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
    public boolean deletarPeloId(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Funcionario funcionario = listarPeloId(id);

            String sql = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";

            PreparedStatement stFuncionario = con.prepareStatement(sql);

            stFuncionario.setInt(1, id);

            int resFuncionario = stFuncionario.executeUpdate();
            int resPessoa = 0;
            if (resFuncionario > 0){
                String sqlPessoa = "DELETE FROM PESSOA WHERE ID_PESSOA = ?";
                PreparedStatement stPessoa = con.prepareStatement(sqlPessoa);
                stPessoa.setInt(1, funcionario.getIdPessoa());
                resPessoa =stPessoa.executeUpdate();
            }else {
                throw new SQLException("Ocorreu um erro na operação");
            }

            return resPessoa > 0;
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

    public boolean buscarCpf(Funcionario funcionario){
        Connection con = null;
        boolean retorno = false;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM Pessoa " +
                    "WHERE cpf = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, funcionario.getCpf());

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
