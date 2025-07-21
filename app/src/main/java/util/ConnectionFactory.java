/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

/**
 *
 * @author adimael
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // Remover bloco static que define valores padrão de URL, USER, PASS
    
    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            java.io.File configFile = new java.io.File("config.properties");
            String url = null;
            String user = null;
            String pass = null;
            if (configFile.exists()) {
                props.load(new FileInputStream(configFile));
                url = props.getProperty("db.url");
                user = props.getProperty("db.user");
                pass = props.getProperty("db.pass");
                // Se algum campo estiver vazio, não conecta!
                if (url == null || url.isBlank() || user == null || user.isBlank() || pass == null) {
                    throw new RuntimeException("Configuração do banco de dados incompleta. Preencha todos os campos na tela de configuração.");
                }
            } else {
                // Se não existe, não conecta!
                throw new RuntimeException("Arquivo config.properties não encontrado. Configure o banco de dados.");
            }
            System.out.println("Tentando conectar ao banco de dados...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexão estabelecida com sucesso!");
            return conn;
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        closeConnection(connection);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        closeConnection(connection);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
}
