package src.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/Tr?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "Algoritimo";  // seu usuário do MySQL
    private static final String PASS = "1234";        // sua senha do MySQL

    public static Connection getConnection() {
        try {
            // Garante que o driver do MySQL seja carregado
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Retorna a conexão com o banco
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MySQL não encontrado. Verifique a dependência Maven.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }
}
