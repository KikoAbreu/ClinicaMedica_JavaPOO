package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Conexao;
import principal.Consulta;


public class ConsultaDAO {
    private Connection conexao;
    MedicoDAO medicoDAO = new MedicoDAO();
    PacienteDAO pacienteDAO = new PacienteDAO();

    public ConsultaDAO() {
        try {
            conexao = Conexao.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consultas (medico_id, paciente_id, data_consulta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getMedico().getId());
            stmt.setInt(2, consulta.getPaciente().getId());
            stmt.setTimestamp(3, new java.sql.Timestamp(consulta.getDataConsulta().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consulta buscarConsulta(int id) {
        Consulta consulta = null;
        String sql = "SELECT * FROM consultas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                consulta = new Consulta();
                consulta.setId(resultado.getInt("id"));
                consulta.setMedico(medicoDAO.buscarMedico(resultado.getInt("medico_id")));
                consulta.setPaciente(pacienteDAO.buscarPaciente(resultado.getInt("paciente_id")));
                consulta.setDataConsulta(resultado.getTimestamp("data_consulta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consulta;
    }

    public List<Consulta> listarConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultado.getInt("id"));
                consulta.setMedico(medicoDAO.buscarMedico(resultado.getInt("medico_id")));
                consulta.setPaciente(pacienteDAO.buscarPaciente(resultado.getInt("paciente_id")));
                consulta.setDataConsulta(resultado.getTimestamp("data_consulta"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    public void atualizarConsulta(Consulta consulta) {
        String sql = "UPDATE consultas SET medico_id = ?, paciente_id = ?, data_consulta = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getMedico().getId());
            stmt.setInt(2, consulta.getPaciente().getId());
            stmt.setTimestamp(3, new java.sql.Timestamp(consulta.getDataConsulta().getTime()));
            stmt.setInt(4, consulta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirConsulta(int id) {
        String sql = "DELETE FROM consultas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

