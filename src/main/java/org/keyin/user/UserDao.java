package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;

import java.sql.*;

public class UserDao {

    // Retrieve a user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("user_role");
                    int id = rs.getInt("user_id");
                    String password = rs.getString("user_password"); // Hashed password
                    String email = rs.getString("user_email");
                    String phoneNumber = rs.getString("user_phone");
                    String address = rs.getString("user_address");

                    // Return correct object type based on role
                    switch (role) {
                        case "Admin":
                            return new Admin(id, username, password, email, phoneNumber, address);
                        case "Trainer":
                            return new Trainer(id, username, password, email, phoneNumber, address);
                        case "Member":
                            return new Member(id, username, password, email, phoneNumber, address);
                        default:
                            return new User(username, password, email, phoneNumber, address, role);
                    }
                }
            }
        }
        return null;
    }

    // Register a new user
    public int registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_name, user_password, email, phone_number, address, user_role) VALUES (?, ?, ?, ?, ?, ?)";
        int userId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("user_id");
                System.out.println("New user registered with user_id = " + userId);
            }
        }
        return userId;
    }
}
