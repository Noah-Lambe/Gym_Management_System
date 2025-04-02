package org.keyin.workoutclasses;

import java.util.List;
import java.util.Scanner;

public class WorkoutClassService {

    private WorkoutClassDAO workoutDAO = new WorkoutClassDAO();

    public void addWorkoutClass(Scanner scanner, int trainerId) {
        System.out.println("\n--- Add Workout Class ---");
        System.out.print("Class Type: ");
        String type = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        WorkoutClass workoutClass = new WorkoutClass();
        workoutClass.setWorkoutClassType(type);
        workoutClass.setWorkoutClassDescription(desc);
        workoutClass.setTrainerId(trainerId);

        workoutDAO.addWorkoutClass(workoutClass);
        System.out.println("✅ Workout class added successfully.");
    }

    public void updateWorkoutClass(Scanner scanner, int trainerId) {
        System.out.print("Enter Workout Class ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New Class Type: ");
        String type = scanner.nextLine();

        System.out.print("New Description: ");
        String desc = scanner.nextLine();

        WorkoutClass workoutClass = new WorkoutClass();
        workoutClass.setWorkoutClassId(id);
        workoutClass.setWorkoutClassType(type);
        workoutClass.setWorkoutClassDescription(desc);
        workoutClass.setTrainerId(trainerId);

        workoutDAO.updateWorkoutClass(workoutClass);
        System.out.println("✅ Workout class updated.");
    }

    public void deleteWorkoutClass(Scanner scanner, int trainerId) {
        System.out.print("Enter Workout Class ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        workoutDAO.deleteWorkoutClass(id);
        System.out.println("🗑️ Workout class deleted.");
    }

    public void viewWorkoutClassesByTrainer(int trainerId) {
        List<WorkoutClass> classes = workoutDAO.getWorkoutClassesByTrainer(trainerId);

        System.out.println("\n--- My Workout Classes ---");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
        }
    }

    public void viewAllWorkoutClasses() {
        List<WorkoutClass> classes = workoutDAO.getAllClasses();

        System.out.println("\n--- All Available Workout Classes ---");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
        }
    }
}
