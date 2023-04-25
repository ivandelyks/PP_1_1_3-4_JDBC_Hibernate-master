package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private String url = "jdbc:mysql://localhost:3306/sys";
    private String username = "root";
    private String password = "151098qaZ)";
    private Connection connect;

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("connection ERROR");
        }
        return connect;
    }

}
