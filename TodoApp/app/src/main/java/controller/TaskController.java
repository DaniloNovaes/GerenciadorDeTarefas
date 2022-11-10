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
 * @author Danilo Novaes
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks (idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET"
                + " idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.getConnection();

            //Cria um PreparedStatment, classe usada para executar a query
            statement = connection.prepareStatement(sql);
             
  
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());

            //Executa a sql para inserção dos dados (query)
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro em atualizar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int taskId) {
        
        String sql = "DELETE FROM tasks WHERE id = ?";
 
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.getConnection();

            //Cria um PreparedStatment, classe usada para executar a query
            statement = connection.prepareStatement(sql);

            //setando os valores
            statement.setInt(1, taskId); // Para substituir a ? pelo taskId passado por parâmetro no RemoveById

            //Executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Task> getAll(int idProject) {

        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        //Lista de Tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();

        try {
           //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            //Setando o valor que corresponde ao filtro de busca ("?")
            statement.setInt(1, idProject);

            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houverem valores a serem percorridos no meu resultSet
            while (resultSet.next()) {

                Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setCreatedAt(resultSet.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar as tarefas" + ex.getMessage() + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //Lista de tarefas que foi criada e carregada do banco de dados        
        return tasks;
    }
}
