package repository;

import model.Atendimento;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import model.Hospital;
import model.exceptions.IdException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepository implements Repositorio<Integer, Atendimento> {


    @Override
    public void cadastrar(Atendimento atendimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoAtendimentoId = this.getProximoId(con, "seq_atendimento.nextval");
            atendimento.setIdAtendimento(proximoAtendimentoId);

            String sqlAtendimento = "INSERT INTO Atendimento\n" +
                    "(id_atendimento, id_hospital, id_paciente, id_medico, data_atendimento, laudo, tipo_de_atendimento, valor_atendimento)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stAtendimento = con.prepareStatement(sqlAtendimento);

            stAtendimento.setInt(1, atendimento.getIdAtendimento());
            stAtendimento.setInt(2, atendimento.getIdHospital());
            stAtendimento.setInt(3, atendimento.getIdPaciente());
            stAtendimento.setInt(4, atendimento.getIdMedico());
            stAtendimento.setDate(5, java.sql.Date.valueOf(atendimento.getDataAtendimento()));
            stAtendimento.setString(6, atendimento.getLaudo());
            stAtendimento.setInt(7, atendimento.getTipoDeAtendimento().getCodigo());
            stAtendimento.setDouble(8, atendimento.getValorDoAtendimento());


            int atendimentosInseridos = stAtendimento.executeUpdate();
            System.out.println("Atendimentos inseridas: " + atendimentosInseridos);

            if (atendimentosInseridos == 0) throw new SQLException("Ocorreu um erro ao inserir!");

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
    public List<Atendimento> listarTodos() throws BancoDeDadosException {
        List<Atendimento> atendimentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ATENDIMENTO\n";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Integer idAtendimento = res.getInt("id_atendimento");
                Integer idHospital = res.getInt("id_hospital");
                Integer idPaciente = res.getInt("id_paciente");
                Integer idMedico = res.getInt("id_medico");
                LocalDate dataAtendimento =   res.getDate("data_atendimento").toLocalDate();
                String laudo = res.getString("laudo");
                Integer tipoDeAtendimento = res.getInt("tipo_de_atendimento");
                Double valorAtendimento = res.getDouble("valor_atendimento");

                String dataFormatada = dataAtendimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Atendimento atendimento = new Atendimento(idHospital, idPaciente, idMedico, dataFormatada, laudo, tipoDeAtendimento);

                atendimento.setIdAtendimento(idAtendimento);
                atendimentos.add(atendimento);
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
        return atendimentos;
    }

    @Override
    public Atendimento listarPeloId(Integer id) throws BancoDeDadosException {
        Atendimento atendimento = new Atendimento();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM ATENDIMENTO\n" +
                    "WHERE ATENDIMENTO.id_atendimento = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);


            ResultSet res = st.executeQuery();
            if (res.next()){
                Integer idAtendimento = res.getInt("id_atendimento");
                Integer idHospital = res.getInt("id_hospital");
                Integer idPaciente = res.getInt("id_paciente");
                Integer idMedico = res.getInt("id_medico");
                LocalDate dataAtendimento =   res.getDate("data_atendimento").toLocalDate();
                String laudo = res.getString("laudo");
                Integer tipoDeAtendimento = res.getInt("tipo_de_atendimento");
                Double valorAtendimento = res.getDouble("valor_atendimento");

                String dataFormatada = dataAtendimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                atendimento = new Atendimento(idHospital, idPaciente, idMedico, dataFormatada, laudo, tipoDeAtendimento);

                atendimento.setIdAtendimento(idAtendimento);
            }

        }catch (SQLException e) {
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
        return atendimento;
    }

    @Override
    public boolean alterarPeloId(Integer id, Atendimento atendimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Atendimento atendimentoId = this.listarPeloId(id);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ATENDIMENTO SET \n");

            List<String> camposAtualizados = new ArrayList<>();
            if (atendimento != null) {
                if (atendimento.getDataAtendimento() != null) {
                    camposAtualizados.add("data_atendimento = ?");
                }
                if (atendimento.getLaudo() != null) {
                    camposAtualizados.add("laudo = ?");
                }
                if (atendimento.getTipoDeAtendimento() != null) {
                    camposAtualizados.add("tipo_de_atendimento = ?");
                }
            }

            if (!camposAtualizados.isEmpty()) {
                sql.append(String.join(", ", camposAtualizados));
                sql.append(" WHERE id_atendimento = ?");

                PreparedStatement st = con.prepareStatement(sql.toString());

                int index = 1;
                if (atendimento != null) {
                    if (atendimento.getDataAtendimento() != null) {
                        st.setDate(index++, java.sql.Date.valueOf(atendimento.getDataAtendimento()));
                    }
                    if (atendimento.getLaudo() != null) {
                        st.setString(index++, atendimento.getLaudo());
                    }
                    if (atendimento.getTipoDeAtendimento() != null) {
                        st.setInt(index++, atendimento.getTipoDeAtendimento().getCodigo());
                    }
                }

                st.setInt(index++, atendimentoId.getIdAtendimento());

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

            Atendimento atendimento = listarPeloId(id);

            String sql = "DELETE FROM ATENDIMENTO WHERE id_atendimento = ?";

            PreparedStatement stAtendimento = con.prepareStatement(sql);

            stAtendimento.setInt(1, id);

            int resAtendimento = stAtendimento.executeUpdate();
            if (resAtendimento > 0){
                String sqlAtendimento = "DELETE FROM ATENDIMENTO WHERE id_atendimento = ?";
                PreparedStatement stAtendimentos = con.prepareStatement(sqlAtendimento);
                stAtendimentos.setInt(1, atendimento.getIdAtendimento());
                resAtendimento =stAtendimentos.executeUpdate();
            }else {
                throw new SQLException("Ocorreu um erro na operação");
            }
            return resAtendimento > 0;
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

    @Override
    public Atendimento buscarId(Integer id) throws BancoDeDadosException {
        return this.listarPeloId(id);
    }
}
