package org.keyin;

import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

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
            System.out.println("Login Successful! Welcome " + user.getUserName());
            switch (user.getUserRole().toLowerCase()) {
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
            System.out.println("3. View all memberships & total revenue");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    userService.viewAllUsers(); // You’ll create this in UserService
                    break;
                case 2:
                    System.out.print("Enter the username to delete: ");
                    String usernameToDelete = scanner.nextLine();
                    userService.deleteUser(usernameToDelete); // You’ll create this too
                    break;
                case 3:
                    membershipService.viewAllMembershipsAndRevenue(); // You’ll also create this
                    break;
                case 4:
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
                    workoutService.addWorkoutClass(scanner, user.getUserId()); // Pass trainer ID
                    break;
                case 2:
                    workoutService.updateWorkoutClass(scanner, user.getUserId());
                    break;
                case 3:
                    workoutService.deleteWorkoutClass(scanner, user.getUserId());
                    break;
                case 4:
                    workoutService.viewWorkoutClassesByTrainer(user.getUserId());
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
                    workoutService.viewAllWorkoutClasses();
                    break;
                case 2:
                    membershipService.viewMembershipExpenses(user.getUserId());
                    break;
                case 3:
                    membershipService.purchaseMembership(scanner, user.getUserId());
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
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(username, password, role);
        userService.addUser(user);
        System.out.println("User added successfully!");
    }

}
