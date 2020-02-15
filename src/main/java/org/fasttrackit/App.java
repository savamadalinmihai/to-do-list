package org.fasttrackit;

import org.fasttrackit.domain.Task;
import org.fasttrackit.persistance.TaskRepository;
import org.fasttrackit.transfer.CreateTaskRequest;
import org.fasttrackit.transfer.UpdateTaskRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        // here we have this object in order to be able to access functions/methods inside it
        TaskRepository taskRepository= new TaskRepository();

// this creates a new task
        CreateTaskRequest request = new CreateTaskRequest();
        request.setDescription("Learn JDBC");
        request.setDeadline(LocalDate.now().plusWeeks(1));
        taskRepository.createTask(request);

// this updates a task
//        UpdateTaskRequest request = new UpdateTaskRequest();
//        request.setDone(true);
//        taskRepository.updateTask(1, request);

// this deletes a task
//        taskRepository.deleteTask(1);

        List<Task> tasks = taskRepository.getTasks();
        System.out.println(tasks);

    }
}
