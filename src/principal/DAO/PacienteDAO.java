package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Conexao;
import principal.Medico;
import principal.Paciente;
public class PacienteDAO {
    private Connection conexao;

    public PacienteDAO() {
        try {
            conexao = Conexao.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes(nome, idade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Paciente> listarPaciente() {
        List<Paciente> Pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(resultado.getInt("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
                Pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Pacientes;
    }

    public Paciente buscarPaciente(int id) {
        Paciente paciente = null;
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                paciente = new Paciente();
                paciente.setId(resultado.getInt("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }


    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setInt(3, paciente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void excluirPaciente(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";
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
