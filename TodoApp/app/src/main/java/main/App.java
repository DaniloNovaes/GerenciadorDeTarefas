/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main;

import com.sun.jndi.ldap.Connection;
import controller.ProjectController;
import controller.TaskController;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

public class App {
    
    public static void main(String[] args) {

        //java.sql.Connection c = ConnectionFactory.getConnection();
        //ConnectionFactory.closeConnection(c);
        //ProjectController projectController = new ProjectController();
        //Project project = new Project();
        //project.setName("Projeto Teste10");
        //project.setDescription("description10");
        //projectController.save(project);
        
TaskController taskController = new TaskController();
        
        Task task = new Task();
        //task.setIdProject(2);
        //task.setId(2);
        //task.setName("Teste Atualização");
        //task.setDescription("description att");
        //task.setIsCompleted(false);
        //task.setNotes("Minha primeira att de note");
        //taskController.update(task);

        //Task task = new Task();
        //task.setIdProject(2);
        //task.setName("TESTE novamente");
        //task.setDescription("teste");
        //taskController.save(task);
        
        //List<Task> tasks = taskController.getAll(2);
        //System.out.println("Total de tarefas = " + tasks.size());
        taskController.removeById(3);
    }
}
