package src.dao;

import src.model.Compromisso;
import src.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompromissoDAO {

    // Inserir compromisso
    public boolean inserir(Compromisso c) {
        String sql = "INSERT INTO compromisso (titulo, data, hora, contato_id, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getTitulo());
            stmt.setDate(2, c.getData());
            stmt.setTime(3, c.getHora());
            stmt.setInt(4, c.getContatoId());
            stmt.setInt(5, c.getCategoriaId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir compromisso:");
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar compromisso
    public boolean atualizar(Compromisso c) {
        String sql = "UPDATE compromisso SET titulo=?, data=?, hora=?, contato_id=?, categoria_id=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getTitulo());
            stmt.setDate(2, c.getData());
            stmt.setTime(3, c.getHora());
            stmt.setInt(4, c.getContatoId());
            stmt.setInt(5, c.getCategoriaId());
            stmt.setInt(6, c.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar compromisso:");
            e.printStackTrace();
            return false;
        }
    }

    // Deletar compromisso
    public boolean deletar(int id) {
        String sql = "DELETE FROM compromisso WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar compromisso:");
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos
    public List<Compromisso> listarTodos() {
        List<Compromisso> lista = new ArrayList<>();
        String sql = "SELECT * FROM compromisso";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Compromisso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("data"),
                        rs.getTime("hora"),
                        rs.getInt("contato_id"),
                        rs.getInt("categoria_id")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar compromissos:");
            e.printStackTrace();
        }
        return lista;
    }

    // Buscar por ID
    public Compromisso buscarPorId(int id) {
        String sql = "SELECT * FROM compromisso WHERE id=?";
        Compromisso c = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Compromisso(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getDate("data"),
                            rs.getTime("hora"),
                            rs.getInt("contato_id"),
                            rs.getInt("categoria_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar compromisso por ID:");
            e.printStackTrace();
        }

        return c;
    }
}
