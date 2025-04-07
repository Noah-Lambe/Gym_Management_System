package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Retrieve a user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String password = rs.getString("user_password");
                    String email = rs.getString("user_email");
                    String phoneNumber = rs.getString("user_phone");
                    String address = rs.getString("user_address");
                    String role = rs.getString("user_role");

                    // Return correct object type based on role
                    switch (role) {
                        case "Admin":
                            return new Admin(id, username, password, email, phoneNumber, address);
                        case "Trainer":
                            return new Trainer(id, username, password, email, phoneNumber, address);
                        case "Member":
                            return new Member(id, username, password, email, phoneNumber, address);
                        default:
                            return new User(id, username, password, email, phoneNumber, address, role);
                    }
                }
            }
        }
        return null;
    }

    // Register a new user and return their user_id
    public int registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_name, user_password, user_email, user_phone, user_address, user_role) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                user.setId(userId);
                return userId;
            }
        }

        return -1;
    }

    // Retrieve all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String password = rs.getString("user_password");
                String email = rs.getString("user_email");
                String phoneNumber = rs.getString("user_phone");
                String address = rs.getString("user_address");
                String role = rs.getString("user_role");

                User user;
                switch (role) {
                    case "Admin":
                        user = new Admin(userId, username, password, email, phoneNumber, address);
                        break;
                    case "Trainer":
                        user = new Trainer(userId, username, password, email, phoneNumber, address);
                        break;
                    case "Member":
                        user = new Member(userId, username, password, email, phoneNumber, address);
                        break;
                    default:
                        user = new User(userId, username, password, email, phoneNumber, address, role);
                        break;
                }

                users.add(user);
            }
        }

        return users;
    }
}
