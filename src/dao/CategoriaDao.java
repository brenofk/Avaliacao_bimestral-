package src.dao;

import src.util.ConnectionFactory;
import src.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Inserir nova categoria
    public boolean inserir(Categoria c) {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria:");
            e.printStackTrace();
            return false;
        }
    }

    // Listar todas as categorias
    public List<Categoria> listarTodos() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias:");
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar categoria por ID
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id=?";
        Categoria c = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Categoria(
                            rs.getInt("id"),
                            rs.getString("nome")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria por ID:");
            e.printStackTrace();
        }

        return c;
    }

    // Atualizar categoria
    public boolean atualizar(Categoria c) {
        String sql = "UPDATE categoria SET nome=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria:");
            e.printStackTrace();
            return false;
        }
    }

    // Deletar categoria
    public boolean deletar(int id) {
        String sql = "DELETE FROM categoria WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar categoria:");
            e.printStackTrace();
            return false;
        }
    }
}
