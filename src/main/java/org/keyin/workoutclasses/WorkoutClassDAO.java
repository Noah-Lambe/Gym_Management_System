package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {
    public void addWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workoutClasses (workoutclasstype, workoutclassdescription, trainerid) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWorkoutCLass(WorkoutClass workoutClass) {
        String sql = "UPDATE workoutclasses SET workoutclasstype =?, workoutclassdescription =?, trainerid =? WHERE workoutclassid =?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());
            pstmt.setInt(4, workoutClass.getWorkoutClassId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWorkoutClass(int workoutClassId) {
        String sql = "DELETE FROM workoutclasses WHERE workoutclassid =?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, workoutClassId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WorkoutClass> getAllClasses() {
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("workoutclassid"),
                        rs.getString("workoutclasstype"),
                        rs.getString("workoutclassdescription"),
                        rs.getInt("trainerid"));
                workoutClasses.add(workoutClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutClasses;
    }

    public List<WorkoutClass> getWorkoutClassesByTrainer(int workoutClassId) {
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses WHERE trainerid =?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, workoutClassId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("workoutclassid"),
                        rs.getString("workoutclasstype"),
                        rs.getString("workoutclassdescription"),
                        rs.getInt("trainerid"));
                workoutClasses.add(workoutClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutClasses;
    }
}
