package org.keyin.user;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private UserDao userDao = new UserDao();

    public void viewAllUsers() {
        System.out.println("\n=== View Users ===");
        System.out.println("(Database user listing not implemented yet)");
    }

    public void deleteUser(String username) {
        System.out.println("User deletion from database not yet implemented.");
    }

    public void addUser(User user) {
        try {
            String insertSql = "INSERT INTO users (user_name, user_password, user_email, user_phone, user_address, user_role) VALUES (?, ?, ?, ?, ?, ?)";
            var conn = org.keyin.database.DatabaseConnection.getConnection();
            var pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());

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
