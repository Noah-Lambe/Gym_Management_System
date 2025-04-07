package org.keyin.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.keyin.database.DatabaseConnection;

public class UserService {
    private UserDao userDao = new UserDao();

    public void viewAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\n=== Registered Users ===");
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhoneNumber());
                System.out.println("Address: " + user.getAddress());
                System.out.println("Role: " + user.getRole());
                System.out.println("-----------------------------");
            }
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE user_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User '" + username + "' deleted successfully.");
            } else {
                System.out.println("No user found with username: " + username);
            }
        }
    }

    public int addUser(User user) {
        try {
            int userId = userDao.registerUser(user);
            System.out.println("User successfully added to the database with ID: " + userId);
            return userId;
        } catch (SQLException e) {
            System.out.println("Error adding user to the database: " + e.getMessage());
            return -1;
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
