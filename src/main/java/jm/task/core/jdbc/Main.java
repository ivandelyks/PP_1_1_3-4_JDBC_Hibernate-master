package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 99);
        userService.saveUser("Zaur", "Kazachkov", (byte) 99);
        userService.saveUser("Ruben", "Pupin", (byte) 99);
        userService.saveUser("Ilya", "Ivanov", (byte) 99);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();


//
//        final String testName = "Ivan";
//        final String testLastName = "Ivanov";
//        final byte testAge = 5;
//
//        userService.saveUser(testName, testLastName,testAge);
//        List<User> usersN = new ArrayList<>();
//        usersN = userService.getAllUsers();
//        System.out.println(usersN);\

    }
}
