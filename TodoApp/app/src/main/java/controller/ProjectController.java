/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Danilo Novaes
 */
public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects (name,"
                + "description,"
                + "createdAt,"
                + "updatedAt)"
                + " VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.getConnection();

            //Cria um PreparedStatment, classe usada para executar a query
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

            //Executa a sql para inserção dos dados
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto " + ex);
        } finally {

            //fecha as conexões
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Project project) {

        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
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

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());

            //Executa a sql para inserção dos dados
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atuaslizar a tarefa" + ex);
        } finally {

            //fecha as conexões
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int projectId) {

        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() {

        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            //Executa
            resultSet = statement.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (resultSet.next()) {

                Project project = new Project();

                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setCreatedAt(resultSet.getDate("updatedAt"));

                //Adiciona o projeto recuperado a lista de projetos
                projects.add(project);

            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar projetos " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        //retorna a lista de projetos.
        return projects;

    }

}
