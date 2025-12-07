package src.dao;

import src.util.ConnectionFactory;
import src.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    // Inserir novo contato
    public boolean inserir(Contato c) {
        String sql = "INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir contato:");
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos os contatos
    public List<Contato> listarTodos() {
        List<Contato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contato";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar contatos:");
            e.printStackTrace();
        }
        return lista;
    }

    // Buscar contato por ID
    public Contato buscarPorId(int id) {
        String sql = "SELECT * FROM contato WHERE id = ?";
        Contato c = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Contato(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contato por ID:");
            e.printStackTrace();
        }

        return c;
    }

    // Atualizar contato existente
    public boolean atualizar(Contato c) {
        String sql = "UPDATE contato SET nome=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setInt(4, c.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar contato:");
            e.printStackTrace();
            return false;
        }
    }

    // Deletar contato pelo ID
    public boolean deletar(int id) {
        String sql = "DELETE FROM contato WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar contato:");
            e.printStackTrace();
            return false;
        }
    }
}
