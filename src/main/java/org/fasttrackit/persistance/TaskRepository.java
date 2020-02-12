package org.fasttrackit.persistance;

import org.fasttrackit.transfer.CreateTaskRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws SQLException {
        // preventing sql injection by avoiding concatenation and using prepared statement
        String sql = "INSERT INTO task (description, deadline) VALUES (?,?)";

        try(Connection connection = DatabaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            //here, we declare which parameters from (?,?) should be replaced with what data.
            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));

            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
