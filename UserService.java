package org.keyin.user;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private UserDao userDao = new UserDao();

    public void viewAllUsers() {
        System.out.println("\n=== View Users ===");
        System.out.println("(Database user listing not implemented yet)");
        // Optional: Later we can add a `getAllUsers()` in UserDao to fetch and list all
        // users.
    }

    public void deleteUser(String username) {
        System.out.println("User deletion from database not yet implemented.");
        // You can add delete logic in UserDao if needed.
    }

    public void addUser(User user) {
        try {
            String insertSql = "INSERT INTO users (user_name, user_password, user_role) VALUES (?, ?, ?)";
            var conn = org.keyin.database.DatabaseConnection.getConnection();
            var pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword()); // Later you can hash this!
            pstmt.setString(3, user.getUserRole());
            pstmt.executeUpdate();

            System.out.println("User successfully added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding user to the database: " + e.getMessage());
        }
    }

    public User loginForUser(String username, String password) {
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return null;
    }
}
