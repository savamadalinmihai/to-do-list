package org.fasttrackit;

import org.fasttrackit.persistance.TaskRepository;
import org.fasttrackit.transfer.CreateTaskRequest;

import java.sql.SQLException;
import java.time.LocalDate;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        TaskRepository taskRepository= new TaskRepository();

        CreateTaskRequest request = new CreateTaskRequest();

        request.setDescription("Learn JDBC");
        request.setDeadline(LocalDate.now().plusWeeks(1));

        taskRepository.createTask(request);
    }
}
