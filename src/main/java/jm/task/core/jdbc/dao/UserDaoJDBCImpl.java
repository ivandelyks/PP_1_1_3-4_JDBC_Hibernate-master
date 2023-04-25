package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = new Util().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Users"
                     + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age INT)")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = new Util().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS Users")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO Users (name,lastName, age)" +
                "VALUES ('" + name + "', '" + lastName + "', '" + String.valueOf(age) +"')";
        Util util = new Util();
        try {
            Connection connection = util.getConnect();
            connection.createStatement().executeUpdate(saveUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = new Util().getConnect();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `Users` WHERE `id` = "+ id);
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM Users";
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try (Connection connection = new Util().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
        ) {
            resultSet = connection.createStatement().executeQuery(getAllUsers);

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = new Util().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE Users");
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}