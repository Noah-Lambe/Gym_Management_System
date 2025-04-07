package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {
    public void addWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workout_classes (class_type, trainer_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setInt(2, workoutClass.getTrainerId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                workoutClass.setWorkoutClassId(generatedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWorkoutClass(WorkoutClass workoutClass) {
        String sql = "UPDATE workout_classes SET class_type =?, class_description =?, trainer_id =? WHERE class_id =?";

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
        String sql = "DELETE FROM workout_classes WHERE class_id =?";

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
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("class_id"),
                        rs.getString("class_type"),
                        rs.getString("class_description"),
                        rs.getInt("trainer_id"));
                workoutClasses.add(workoutClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutClasses;
    }

    public List<WorkoutClass> getWorkoutClassesByTrainer(int workoutClassId) {
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id =?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, workoutClassId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("class_id"),
                        rs.getString("class_type"),
                        rs.getString("class_description"),
                        rs.getInt("trainer_id"));
                workoutClasses.add(workoutClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutClasses;
    }
}
