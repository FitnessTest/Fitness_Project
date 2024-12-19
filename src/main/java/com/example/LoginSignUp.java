package com.example;

import java.util.*;

public class LoginSignUp {
    // User roles enumeration
    public enum Role {
        ADMIN, INSTRUCTOR, CLIENT
    }

    // User class to store account details
    public static class User extends UserManagement.User {
        String id;
        String password;
        String role;

        public User(String id, String password, String name, String email, String role) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.role = role;
        }
    }

    // Database simulation
    private static List<UserManagement.User> users = new ArrayList<>();
    private static UserManagement userManagement = new UserManagement(users);

    // Method to validate ID format based on role1
    private static boolean isValidID(String id, Role role) {
        if (role == Role.ADMIN) {
            return true; // Admin can have any length of ID
        } else if (role == Role.INSTRUCTOR) {
            return id.matches("\\d{3}"); // Exactly 3 digits
        } else if (role == Role.CLIENT) {
            return id.matches("\\d{5}"); // Exactly 9 digits
        }
        return false;
    }

    // Method to check if ID is unique
    private static boolean isUniqueID(String id) {
        for (UserManagement.User user : users) {
            if (user.id.equals(id)) return false;
        }
        return true;
    }

    // Signup method for Clients only
    public static void signUp(Scanner scanner) {
        System.out.println("\n--- Client Signup ---");
        System.out.print("Enter ID (5 digits): ");
        String id = scanner.nextLine();

        if (!isValidID(id, Role.CLIENT)) {
            System.out.println("Invalid ID format. Client IDs must be exactly 5 digits.");
            return;
        }
        if (!isUniqueID(id)) {
            System.out.println("ID already exists. Please try a different ID.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        users.add(new UserManagement.User(id, password, name, email, "CLIENT"));
        userManagement.createUserAccount(id, password, name, email, "CLIENT");
        System.out.println("Account created successfully! You can now log in.");
    }

    // Menu to handle user login process
    public static void logInMenu(Scanner scanner) {
        System.out.println("\n--- Login Menu ---");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        logIn(id, password, scanner);
    }

    // Login method for all roles
    public static void logIn(String id, String password, Scanner scanner) {
        for (UserManagement.User user : users) {
            if (user.id.equals(id) && user.password.equals(password)) {
                System.out.println("Login successful! Welcome, " + user.role + ".");
                if (user.role.equals("ADMIN")) {
                    adminMenu(scanner);
            }
                return;
            }
        }
        System.out.println("Invalid ID or password. Please try again.");
    }

    // Admin menu
    public static void adminMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. User Management");
            System.out.println("2. Program Monitoring");
            System.out.println("3. Content Management");
            System.out.println("4. Subscription Management");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userManagementMenu(scanner);
                    break;
                case "2":
                    ProgramMonitoring programMonitoring = new ProgramMonitoring();
                    boolean programExit = false;
                    while (!programExit) {
                        System.out.println("\nProgram Monitoring Menu:");
                        System.out.println("1. View Most Popular Programs");
                        System.out.println("2. Generate Reports");
                        System.out.println("3. Track Program Status");
                        System.out.println("4. Back to Admin Menu");
                        System.out.print("Enter your choice: ");

                        int programChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        switch (programChoice) {
                            case 1:
                                System.out.println("Most Popular Programs:");
                                for (String line : programMonitoring.viewMostPopularPrograms()) {
                                    System.out.println(line);
                                }
                                break;
                            case 2:
                                programMonitoring.generateReports();
                                break;
                            case 3:
                                programMonitoring.trackProgramsStatus();
                                break;
                            case 4:
                                programExit = true;
                                System.out.println("Returning to Admin Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;
                case "3":
                    ContentManagement contentManagement = new ContentManagement();
                    contentManagement.viewAllContent();
                    contentManagement.viewAllComplaints();
                    contentManagement.handleComplaint("Sample complaint message");
                    break;
                case "4":
                    System.out.println("Subscription Management features here...");
                    break;
                case "5":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // User Management Menu
    public static void userManagementMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Create User Account");
            System.out.println("2. Delete User Account");
            System.out.println("3. View User Accounts");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Role: ");
                    String role = scanner.nextLine().toUpperCase();
                    userManagement.createUserAccount(id, password, name, email, role);
                    break;
                case "2":
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine();
                    userManagement.deleteUserAccount(deleteId);
                    break;
                case "3":
                    userManagement.viewUserAccounts();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        userManagement.createUserAccount("1", "ihab", "Ihab", "kebab83@gmail.com", "ADMIN");
        userManagement.createUserAccount("2", "amr", "Amr", "amrojamhour4@gmail.com", "ADMIN");
        userManagement.createUserAccount("111", "ameed", "Ameed", "ameed@gmail.com", "INSTRUCTOR");
        userManagement.createUserAccount("11111", "zahi", "Zahi", "zahi@gmail.com", "CLIENT");

        while (running) {
            System.out.println("\n--- Login/Signup System ---");
            System.out.println("1. Login");
            System.out.println("2. Sign Up (Clients Only)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logInMenu(scanner);
                    break;
                case "2":
                    signUp(scanner);
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
