/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author adimael
 */
public class TaskController {
    
    public void save(Task task){
        
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        //Cria uma conexão com o banco
        Connection connection = null;
        //Cria um PreparedStatment, classe usada para executar a query
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreated_at().getTime()));
            statement.setDate(8, new Date(task.getUpdated_at().getTime()));
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa! :( " + ex.getMessage(), ex);
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Task task){
        
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, completed = ?, deadline = ?, created_at = ?, updated_at = ? WHERE id = ?";
        
        //Cria uma conexão com o banco
        Connection connection = null;
        //Cria um PreparedStatment, classe usada para executar a query
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreated_at().getTime()));
            statement.setDate(8, new Date(task.getUpdated_at().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
            
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar a tarefa! :( ", ex);
        }
        
    }
    
    public void removeById(int id){
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar uma tarefa! :( " + ex.getMessage(), ex);
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()){
                
                   Task task = new Task();
                   task.setId(resultSet.getInt("id"));
                   task.setIdProject(resultSet.getInt("idProject"));
                   task.setName(resultSet.getString("name"));
                   task.setDescription(resultSet.getString("description"));
                   task.setNotes(resultSet.getString("notes"));
                   task.setCompleted(resultSet.getBoolean("completed"));
                   task.setDeadline(resultSet.getDate("deadline"));
                   task.setCreated_at(resultSet.getDate("created_at"));
                   task.setUpdated_at(resultSet.getDate("updated_at"));
                   
                   tasks.add(task);
                
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir a tarefa! :( " + ex.getMessage(), ex);
        } finally{
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }
    
}
