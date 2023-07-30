package repository;
import model.exceptions.BancoDeDadosException;
import model.Medico;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository implements Repositorio<Integer, Medico> {


    @Override
    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException {
        try {
            String sql = "SELECT " + nextSequence + " mysequence from DUAL";
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

    @Override
    public void cadastrar(Medico medico) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoPessoaId = this.getProximoId(con, "seq_pessoa.nextval");
            medico.setIdPessoa(proximoPessoaId);

            String sqlPessoa = "INSERT INTO Pessoa\n" +
                    "(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stPesssoa = con.prepareStatement(sqlPessoa);

            stPesssoa.setInt(1, medico.getIdPessoa());
            stPesssoa.setString(2, medico.getNome());
            stPesssoa.setString(3, medico.getCep());
            stPesssoa.setDate(4, java.sql.Date.valueOf(medico.getDataNascimento()));
            stPesssoa.setString(5, medico.getCpf());
            stPesssoa.setDouble(6, medico.getSalarioMensal());


            int pessoasInseridas = stPesssoa.executeUpdate();
            System.out.println("Pessoas inseridas: " + pessoasInseridas);

            if (pessoasInseridas == 0) throw new SQLException("Ocorreu um erro ao inserir!");

            Integer proximoMedicoId = this.getProximoId(con, "seq_medico.nextval");
            medico.setIdMedico(proximoMedicoId);

            String sql = "INSERT INTO Medico\n" +
                    "(id_medico, id_hospital, id_pessoa, crm)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stMedico = con.prepareStatement(sql);
            stMedico.setInt(1, medico.getIdMedico());
            stMedico.setInt(2, 1);
            stMedico.setInt(3, medico.getIdPessoa());
            stMedico.setString(4, medico.getCrm());

            int res = stMedico.executeUpdate();
            System.out.println("Medicos inseridos:" + res);

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
    public List<Medico> listarTodos() throws BancoDeDadosException {
        List<Medico> medicos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM MEDICO\n" +
                    "INNER JOIN PESSOA\n" +
                    "ON PESSOA.ID_PESSOA = MEDICO.ID_PESSOA";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Integer idPessoa = res.getInt("id_pessoa");
                String nome = res.getString("nome");
                String cep = res.getString("cep");
                LocalDate data =   res.getDate("data_nascimento").toLocalDate();
                String cpf = res.getString("cpf");
                Double salarioMensal = res.getDouble("salario_mensal");
                Integer idMedico = res.getInt("id_medico");
                Integer idHospital = res.getInt("id_hospital");
                String crm = res.getString("crm");

                String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Medico medico = new Medico(nome, cep, dataFormatada, cpf, salarioMensal, idHospital, crm);

                medico.setIdPessoa(idPessoa);
                medico.setIdMedico(idMedico);

                medicos.add(medico);
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
        return medicos;
    }

    @Override
    public Medico listarPeloId(Integer id) throws BancoDeDadosException {
        Medico medico = new Medico();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM MEDICO\n" +
                    "INNER JOIN PESSOA ON MEDICO.ID_MEDICO = ? AND PESSOA.ID_PESSOA = MEDICO.ID_PESSOA\n";

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
                Integer idMedico = res.getInt("id_medico");
                Integer idHospital = res.getInt("id_hospital");
                String crm = res.getString("crm");

                String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                medico = new Medico(nome,cep, dataFormatada, cpf, salarioMensal, idHospital, crm);

                medico.setIdPessoa(idPessoa);
                medico.setIdMedico(idMedico);
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
        return medico;
    }

    @Override
    public boolean alterarPeloId(Integer id, Medico medico) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Medico medicoId = this.listarPeloId(id);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET \n");
            List<String> camposAtualizados = new ArrayList<>();
            if (medico != null) {
                if (medico.getNome() != null) {
                    camposAtualizados.add("nome = ?");
                }
                if (medico.getCep() != null) {
                    camposAtualizados.add("cep = ?");
                }
                if (medico.getDataNascimento() != null) {
                    camposAtualizados.add("data_nascimento = ?");
                }
                if (medico.getCpf() != null) {
                    camposAtualizados.add("cpf = ?");
                }
                if (medico.getSalarioMensal() != null) {
                    camposAtualizados.add("salario_mensal = ?");
                }
            }

            if (!camposAtualizados.isEmpty()) {
                sql.append(String.join(", ", camposAtualizados));
                sql.append(" WHERE id_pessoa = ?");

                PreparedStatement st = con.prepareStatement(sql.toString());

                int index = 1;
                if (medico != null) {
                    if (medico.getNome() != null) {
                        st.setString(index++, medico.getNome());
                    }
                    if (medico.getCep() != null) {
                        st.setString(index++, medico.getCep());
                    }
                    if (medico.getDataNascimento() != null) {
                        st.setDate(index++, java.sql.Date.valueOf(medico.getDataNascimento()));
                    }
                    if (medico.getCpf() != null) {
                        st.setString(index++, medico.getCpf());
                    }
                    if (medico.getSalarioMensal() != null) {
                        st.setDouble(index++, medico.getSalarioMensal());
                    }
                }

                st.setInt(index++, medicoId.getIdPessoa());

                int res = st.executeUpdate();
                System.out.println("Update: " + res);



                String sqlMedico = "UPDATE MEDICO SET crm = ?\n" +
                        "WHERE MEDICO.id_medico= ?";
                if (medico.getCrm() != null){
                    st.setString(1, medico.getCrm());
                }
                PreparedStatement statement = con.prepareStatement(sqlMedico);
                statement.setString(1, medico.getCrm());
                statement.setInt(2,id);

                int res2 = statement.executeUpdate();

                return res2 > 0;
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
                throw new BancoDeDadosException(e.getCause());
            }
        }
    }

    @Override
    public boolean deletarPeloId(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Medico medico = listarPeloId(id);

            String sql = "DELETE FROM MEDICO WHERE ID_MEDICO = ?";

            PreparedStatement stMedico = con.prepareStatement(sql);

            stMedico.setInt(1, id);

            int resMedico = stMedico.executeUpdate();
            int resPessoa = 0;
            if (resMedico > 0){
                String sqlPessoa = "DELETE FROM PESSOA WHERE ID_PESSOA = ?";
                PreparedStatement stPessoa = con.prepareStatement(sqlPessoa);
                stPessoa.setInt(1, medico.getIdPessoa());
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

    public boolean buscarCpf(Medico medico){
        Connection con = null;
        boolean retorno = false;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM Pessoa " +
                    "WHERE cpf = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, medico.getCpf());

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
