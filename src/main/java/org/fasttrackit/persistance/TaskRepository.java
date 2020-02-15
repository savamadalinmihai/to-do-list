package org.fasttrackit.persistance;

import org.fasttrackit.domain.Task;
import org.fasttrackit.transfer.CreateTaskRequest;
import org.fasttrackit.transfer.UpdateTaskRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws SQLException, ClassNotFoundException {
        // preventing sql injection by avoiding concatenation and using prepared statement
        String sql = "INSERT INTO task (description, deadline) VALUES (?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //here, we declare which parameters from (?,?) should be replaced with what data.
            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));

            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(long id, UpdateTaskRequest request) throws SQLException, ClassNotFoundException {
        // preventing sql injection by avoiding concatenation and using prepared statement
        String sql = "UPDATE task SET done = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //here, we declare which parameters from (?,?) should be replaced with what data.
            preparedStatement.setBoolean(1, request.isDone());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(long id) throws SQLException, ClassNotFoundException {
        // preventing sql injection by avoiding concatenation and using prepared statement
        String sql = "DELETE FROM task WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //here, we declare which parameters from (?,?) should be replaced with what data.
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getTasks() throws IOException, SQLException, ClassNotFoundException {
        // statement should be used only for no parameter queries.
        String sql = "SELECT id, description, deadline, done FROM task";
        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Task> tasks = new ArrayList<>();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getDate("deadline").toLocalDate());
                task.setDone(resultSet.getBoolean("done"));

                tasks.add(task);
            }
            return tasks;
        }
    }
}
