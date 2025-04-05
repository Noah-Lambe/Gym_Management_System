package org.keyin;

import org.keyin.database.DatabaseConnection;
import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

        DatabaseConnection.initializeDatabase("src/main/resources/scripts.sql");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, membershipService, workoutService);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.loginForUser(username, password);
        if (user != null) {
            System.out.println("Login Successful! Welcome " + user.getUsername());
            switch (user.getRole().toLowerCase()) {
                case "admin":
                    showAdminMenu(scanner, user, userService, membershipService, workoutService);
                    break;
                case "trainer":
                    showTrainerMenu(scanner, user, userService, workoutService);
                    break;
                case "member":
                    showMemberMenu(scanner, user, userService, membershipService, workoutService);
                    break;
                default:
                    System.out.println("Invalid role.");
            }
        } else {
            System.out.println("Login Failed! Invalid credentials.");
        }

    }

    private static void showAdminMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View all memberships");
            System.out.println("4. View all total revenue");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userService.viewAllUsers();
                    break;
                case 2:
                    System.out.print("Enter the username to delete: ");
                    String usernameToDelete = scanner.nextLine();
                    userService.deleteUser(usernameToDelete);
                    break;
                case 3:
                    membershipService.getAllMemberships().forEach(System.out::println);
                    break;
                case 4:
                    System.out.printf("Total Revenue: %.2f%n", membershipService.getTotalRevenue());
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void showTrainerMenu(Scanner scanner, User user, UserService userService,
            WorkoutClassService workoutService) {
        int choice;
        do {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. Add Workout Class");
            System.out.println("2. Update Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("4. View My Workout Classes");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    WorkoutClass newClass = new WorkoutClass();
                    System.out.print("Enter workout type: ");
                    newClass.setWorkoutClassType(scanner.nextLine());

                    workoutService.addWorkoutClass(newClass);
                    break;

                case 2:
                    WorkoutClass updatedClass = new WorkoutClass();

                    System.out.print("Enter the ID of the workout class to update: ");
                    int classId = scanner.nextInt();
                    scanner.nextLine();

                    updatedClass.setWorkoutClassId(classId);

                    System.out.print("Enter new workout type: ");
                    updatedClass.setWorkoutClassType(scanner.nextLine());

                    workoutService.updateWorkoutClass(updatedClass);
                    break;

                case 3:
                    System.out.print("Enter the workout class ID to delete: ");
                    int classIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    workoutService.deleteWorkoutClass(classIdToDelete);

                    break;
                case 4:
                    workoutService.getWorkoutClassesByTrainer(user.getId());
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void showMemberMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService,
            WorkoutClassService workoutService) {
        int choice;
        do {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Browse Workout Classes");
            System.out.println("2. View My Membership Expenses");
            System.out.println("3. Purchase Membership");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    workoutService.getAllWorkoutClasses();
                    break;
                case 2:
                    membershipService.viewMembershipExpenses(user.getId());
                    break;
                case 3:
                    Membership newMembership = new Membership();
                    System.out.print("Enter membership cost: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine();
                    newMembership.setCost(cost);

                    membershipService.purchaseMembership(newMembership);

                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(username, password, email, phoneNumber, address, role);
        userService.addUser(user);
        // System.out.println("User added successfully!");
    }
}
