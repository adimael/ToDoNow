package controller;

import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;

public class UserController {
    public void save(String nome, String usuario, String senha) {
        String sql = "INSERT INTO usuarios (nome, usuario, senha) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, usuario);
            String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
            statement.setString(3, senhaHash);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar usuário: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE usuario = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String hash = resultSet.getString("senha");
                return BCrypt.checkpw(senha, hash);
            }
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao autenticar usuário: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }

    // Gera e salva um token de autologin para o usuário
    public String gerarSalvarTokenAutologin(String usuario) {
        String token = UUID.randomUUID().toString();
        String sql = "UPDATE usuarios SET autologin_token = ? WHERE usuario = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, usuario);
            statement.executeUpdate();
            return token;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar token de autologin: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    // Busca usuário pelo token de autologin
    public String buscarUsuarioPorToken(String token) {
        String sql = "SELECT usuario FROM usuarios WHERE autologin_token = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("usuario");
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar usuário por token: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }

    // Limpa o token de autologin do usuário (logout)
    public void limparTokenAutologin(String usuario) {
        String sql = "UPDATE usuarios SET autologin_token = NULL WHERE usuario = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao limpar token de autologin: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
} 