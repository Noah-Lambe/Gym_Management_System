package org.keyin.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gym_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "6624";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase(String sqlFilePath) {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
            for (String command : sql.split(";")) {
                if (!command.trim().isEmpty()) {
                    stmt.execute(command.trim());
                }
            }

            System.out.println("Database initialized successfully.");

        } catch (SQLException | IOException e) {
            System.err.println("Failed to initialize database:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("Connection successful");

            // Run SQL script to create tables
            initializeDatabase("src/main/resources/schema.sql");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
