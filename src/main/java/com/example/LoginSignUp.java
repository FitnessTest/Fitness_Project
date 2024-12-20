package com.example;

import java.util.*;

public class LoginSignUp {
    public enum Role {
        ADMIN, INSTRUCTOR, CLIENT
    }


    // User class to store account details
    public static class User extends UserManagement.User {
        // Constructor to initialize User object
        public User(String id, String password, String name, String email, String role) {
            super(id, password, name, email, role); // Call parent constructor
        }
    }

    private static List<UserManagement.User> users = new ArrayList<>();
    private static UserManagement userManagement = new UserManagement(users);

    // Method to validate the ID based on the role
    private static boolean isValidID(String id, Role role) {
        if (role == Role.ADMIN) {
            return true; // Admin IDs can be any format, adjust if needed
        } else if (role == Role.INSTRUCTOR) {
            return id.matches("\\d{3}"); // Instructors must have a 3-digit ID
        } else if (role == Role.CLIENT) {
            return id.matches("\\d{5}"); // Clients must have a 5-digit ID
        }
        return false;
    }

    // Method to check if the ID is unique
    private static boolean isUniqueID(String id) {
        return users.stream().noneMatch(user -> user.id.equals(id));
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

        // Add user to both list and UserManagement
        User newUser = new User(id, password, name, email, "CLIENT");
        users.add(newUser);
        userManagement.addUser(id, password, name, email, "CLIENT");

        System.out.println("Account created successfully! You can now log in.");
    }

    // Menu to handle user login process
    public static void logInMenu(Scanner scanner) {
        System.out.println("\n--- Login Menu ---");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Access the users list from UserManagement
        for (UserManagement.User user : userManagement.getUsers()) {
            if (user.id.equals(id) && user.password.equals(password)) {
                System.out.println("Login successful! Welcome, " + user.role + ".");
                switch (user.role) {
                    case "ADMIN":
                        adminMenu(scanner);
                        break;
                    case "INSTRUCTOR":
                        instructorMenu(scanner);
                        break;
                    case "CLIENT":
                        clientMenu(scanner);
                        break;
                    default:
                        System.out.println("Unknown role.");
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
                    programMonitoringMenu(scanner);
                    break;
                case "3":
                    contentManagementMenu(scanner);
                    break;
                case "4":
                    subscriptionManagementMenu(scanner);
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

    public static void userManagementMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Create User Account");
            System.out.println("2. Update User Account");
            System.out.println("3. Deactivate User Account");
            System.out.println("4. View User Accounts");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter user ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter role (Instructor/Client): ");
                    String role = scanner.nextLine();
                    userManagement.addUser(id, password, name, email, role);
                    break;

                case "2":
                    System.out.println("Enter user ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.println("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    userManagement.updateUser(updateId, newName, newEmail, newPassword);
                    break;

                case "3":
                    System.out.println("Enter user ID to deactivate: ");
                    String deactivateId = scanner.nextLine();
                    userManagement.deactivateUser(deactivateId);
                    break;

                case "4":
                    userManagement.viewEngagementStats();
                    break;

                case "5":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void programMonitoringMenu(Scanner scanner) {
        boolean programExit = false;
        while (!programExit) {
            System.out.println("\n--- Program Monitoring Menu ---");
            System.out.println("1. View Most Popular Programs");
            System.out.println("2. Generate Reports");
            System.out.println("3. Track Program Status");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");

            int programChoice;
            try {
                programChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (programChoice) {
                case 1:
                    System.out.println("Most Popular Programs:");
                    for (String line : ProgramMonitoring.viewMostPopularPrograms()) {
                        System.out.println(line);
                    }
                    break;

                case 2:
                    ProgramMonitoring.generateReports();
                    break;

                case 3:
                    ProgramMonitoring.trackProgramsStatus();
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
    }

    public static void contentManagementMenu(Scanner scanner) {
        boolean running = true;
        ContentManagement cm = new ContentManagement(); // Create an instance of ContentManagement

        while (running) {
            System.out.println("\n--- Content Management ---");
            System.out.println("1. Approve or Reject Articles");
            System.out.println("2. Handle Feedback and Complaints");
            System.out.println("3. View All Content");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the title of the content to review: ");
                    String title = scanner.nextLine();
                    System.out.print("Approve (yes/no): ");
                    String approve = scanner.nextLine().trim().toLowerCase();
                    boolean isApproved = "yes".equals(approve);
                    cm.approveOrRejectContent(title, isApproved); // Use the instance method
                    break;

                case "2":
                    System.out.println("\n--- Handle Feedback and Complaints ---");
                    System.out.println("1. Add Feedback");
                    System.out.println("2. Add Complaint");
                    System.out.println("3. View All Feedback");
                    System.out.println("4. View All Complaints");
                    System.out.print("Choose an option: ");
                    String subChoice = scanner.nextLine();

                    switch (subChoice) {
                        case "1":
                            System.out.print("Enter feedback: ");
                            String feedback = scanner.nextLine();
                            cm.handleFeedback(feedback); // Use the instance method
                            break;

                        case "2":
                            System.out.print("Enter complaint: ");
                            String complaint = scanner.nextLine();
                            cm.handleComplaint(complaint); // Use the instance method
                            break;

                        case "3":
                            System.out.println("\nAll Feedback:");
                            for (String fb : cm.viewAllFeedback()) { // Use the instance method
                                System.out.println("- " + fb);
                            }
                            break;

                        case "4":
                            System.out.println("\nAll Complaints:");
                            for (String comp : cm.viewAllComplaints()) { // Use the instance method
                                System.out.println("- " + comp);
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case "3":
                    System.out.println("\nAll Content:");
                    for (String contentStatus : cm.viewAllContent()) { // Use the instance method
                        System.out.println("- " + contentStatus);
                    }
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void subscriptionManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Subscription Management ---");
            System.out.println("1. View All Subscriptions");
            System.out.println("2. Upgrade to Premium");
            System.out.println("3. Downgrade to Basic");
            System.out.println("4. Change Subscription Plan");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    SubscriptionManagement.viewAllSubscriptions();
                    break;

                case "2":
                    System.out.print("Enter User ID to upgrade to Premium: ");
                    String upgradeId = scanner.nextLine();
                    SubscriptionManagement.upgradeToPremium(upgradeId);
                    break;

                case "3":
                    System.out.print("Enter User ID to downgrade to Basic: ");
                    String downgradeId = scanner.nextLine();
                    SubscriptionManagement.downgradeToBasic(downgradeId);
                    break;

                case "4":
                    System.out.print("Enter User ID to change subscription plan: ");
                    String changeId = scanner.nextLine();
                    System.out.print("Enter new plan (BASIC or PREMIUM): ");
                    String newPlanStr = scanner.nextLine().toUpperCase();
                    try {
                        SubscriptionManagement.Plan newPlan = SubscriptionManagement.Plan.valueOf(newPlanStr);
                        SubscriptionManagement.changeSubscriptionPlan(changeId, newPlan);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid plan entered. Please enter either BASIC or PREMIUM.");
                    }
                    break;

                case "5":
                    System.out.println("Returning to Main Menu...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void instructorMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. Program Management");
            System.out.println("2. Client Interaction");
            System.out.println("3. Progress Tracking");
            System.out.println("4. Notifications and Updates");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    programManagementMenu(scanner);
                    break;
                case "2":
                    clientInteractionMenu(scanner);
                    break;
                case "3":
                    progressTrackingMenu(scanner);
                    break;
                case "4":
                    notificationsAndUpdatesMenu(scanner);
                    break;
                case "5":
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   public static void programManagementMenu(Scanner scanner) {
            boolean exit = false;

            while (!exit) {
                System.out.println("\n--- Program Management Menu ---");
                System.out.println("1. Create a New Program");
                System.out.println("2. Update an Existing Program");
                System.out.println("3. Delete a Program");
                System.out.println("4. List All Programs");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter details for the new program:");
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Duration: ");
                        String duration = scanner.nextLine();
                        System.out.print("Difficulty Level: ");
                        String difficultyLevel = scanner.nextLine();
                        System.out.print("Goals: ");
                        String goals = scanner.nextLine();
                        System.out.print("Video Tutorial: ");
                        String videoTutorial = scanner.nextLine();
                        System.out.print("Image: ");
                        String image = scanner.nextLine();
                        System.out.print("Document: ");
                        String document = scanner.nextLine();
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Schedule: ");
                        String schedule = scanner.nextLine();

                        ProgramManagement.createProgram(title, duration, difficultyLevel, goals,
                                videoTutorial, image, document, price, schedule);
                        break;

                    case 2:
                        System.out.print("Enter the title of the program to update: ");
                        String oldTitle = scanner.nextLine();
                        System.out.println("Enter new details for the program:");
                        System.out.print("New Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("New Duration: ");
                        String newDuration = scanner.nextLine();
                        System.out.print("New Difficulty Level: ");
                        String newDifficultyLevel = scanner.nextLine();
                        System.out.print("New Goals: ");
                        String newGoals = scanner.nextLine();
                        System.out.print("New Video Tutorial: ");
                        String newVideoTutorial = scanner.nextLine();
                        System.out.print("New Image: ");
                        String newImage = scanner.nextLine();
                        System.out.print("New Document: ");
                        String newDocument = scanner.nextLine();
                        System.out.print("New Price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("New Schedule: ");
                        String newSchedule = scanner.nextLine();

                        ProgramManagement.updateProgram(oldTitle, newTitle, newDuration, newDifficultyLevel,
                                newGoals, newVideoTutorial, newImage, newDocument, newPrice, newSchedule);
                        break;

                    case 3:
                        System.out.print("Enter the title of the program to delete: ");
                        String deleteTitle = scanner.nextLine();
                        ProgramManagement.deleteProgram(deleteTitle);
                        break;

                    case 4:
                        ProgramManagement.listAllPrograms();
                        break;

                    case 5:
                        exit = true;
                        System.out.println("Returning to Main Menu...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

    public static void clientInteractionMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Client Interaction Menu ---");
            System.out.println("1. Add a New Client");
            System.out.println("2. Send Message to a Client");
            System.out.println("3. Provide Progress Report to a Client");
            System.out.println("4. List All Clients");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter details for the new client:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    ClientInteraction.addClient(name, email);
                    break;

                case 2:
                    System.out.print("Enter the email of the client to send a message: ");
                    String clientEmailToMessage = scanner.nextLine();
                    System.out.print("Enter the message: ");
                    String message = scanner.nextLine();
                    ClientInteraction.sendMessageToClient(clientEmailToMessage, message);
                    break;

                case 3:
                    System.out.print("Enter the email of the client to send a progress report: ");
                    String clientEmailForReport = scanner.nextLine();
                    System.out.print("Enter the progress report: ");
                    String report = scanner.nextLine();
                    ClientInteraction.provideProgressReport(clientEmailForReport, report);
                    break;

                case 4:
                    ClientInteraction.listAllClients();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void progressTrackingMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Progress Tracking Menu ---");
            System.out.println("1. Add a New Client");
            System.out.println("2. Update Completed Sessions for a Client");
            System.out.println("3. Send Motivational Reminder to a Client");
            System.out.println("4. Monitor All Client Progress");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter details for the new client:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Total Sessions: ");
                    int totalSessions = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        ProgressTracking.addClient(new ProgressTracking.Client(name, email, totalSessions));
                        System.out.println("Client added successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error adding client: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the email of the client: ");
                    String clientEmailToUpdate = scanner.nextLine();
                    System.out.print("Enter the number of completed sessions: ");
                    int sessionsCompleted = scanner.nextInt();
                    scanner.nextLine();

                    ProgressTracking.updateCompletedSessions(clientEmailToUpdate, sessionsCompleted);
                    break;

                case 3:
                    System.out.print("Enter the email of the client to send a reminder: ");
                    String clientEmailToMessage = scanner.nextLine();
                    System.out.print("Enter the motivational message: ");
                    String message = scanner.nextLine();

                    if (ProgressTracking.sendMotivationalReminder(clientEmailToMessage, message)) {
                        System.out.println("Motivational reminder sent successfully.");
                    } else {
                        System.out.println("Client not found.");
                    }
                    break;

                case 4:
                    ProgressTracking.monitorClientProgress();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    public static void notificationsAndUpdatesMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Notifications and Updates Menu ---");
            System.out.println("1. Add a New Client");
            System.out.println("2. Notify Schedule Change");
            System.out.println("3. Announce New Program or Offer");
            System.out.println("4. List All Clients and Notifications");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Client Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Client Email: ");
                    String email = scanner.nextLine();
                    NotificationsAndUpdates.addClient(name, email);
                    break;

                case 2:
                    System.out.print("Enter Program Title: ");
                    String programTitle = scanner.nextLine();
                    System.out.print("Enter New Schedule: ");
                    String newSchedule = scanner.nextLine();
                    NotificationsAndUpdates.notifyScheduleChange(programTitle, newSchedule);
                    break;

                case 3:
                    System.out.print("Enter Announcement: ");
                    String announcement = scanner.nextLine();
                    NotificationsAndUpdates.announceNewProgramOrOffer(announcement);
                    break;

                case 4:
                    NotificationsAndUpdates.listAllClientsNotifications();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }



    // Client menu
    public static void clientMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Account Management");
            System.out.println("2. Program Exploration and Enrollment");
            System.out.println("3. Progress Tracking");
            System.out.println("4. Feedback and Reviews");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    accountManagementMenu(scanner);
                    break;
                case "2":
                    programExplorationMenu(scanner);
                    break;
                case "3":
                    ProgressTrackingClientMenu(scanner);
                    break;
                case "4":
                    FeedbackAndReviewsClientMenu(scanner);
                    break;
                case "5":
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    public static void accountManagementMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Account Management Menu ---");
            System.out.println("1. Create Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View Profile");
            System.out.println("4. Delete Profile");
            System.out.println("5. List All Profiles");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter Fitness Goals: ");
                    String fitnessGoals = scanner.nextLine();
                    System.out.print("Enter Dietary Preferences: ");
                    String dietaryPreferences = scanner.nextLine();
                    AccountManagement.createProfile(name, email, age, fitnessGoals, dietaryPreferences);
                    break;

                case 2:
                    System.out.print("Enter Email of Profile to Update: ");
                    String emailToUpdate = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter New Fitness Goals: ");
                    String newFitnessGoals = scanner.nextLine();
                    System.out.print("Enter New Dietary Preferences: ");
                    String newDietaryPreferences = scanner.nextLine();
                    AccountManagement.updateProfile(emailToUpdate, newName, newAge, newFitnessGoals, newDietaryPreferences);
                    break;

                case 3:
                    System.out.print("Enter Email to View Profile: ");
                    String emailToView = scanner.nextLine();
                    AccountManagement.ClientProfile profile = AccountManagement.viewProfile(emailToView);
                    if (profile != null) {
                        System.out.println("Profile Details:");
                        System.out.println(profile);
                    }
                    break;

                case 4:
                    System.out.print("Enter Email to Delete Profile: ");
                    String emailToDelete = scanner.nextLine();
                    AccountManagement.deleteProfile(emailToDelete);
                    break;

                case 5:
                    AccountManagement.listAllProfiles();
                    break;

                case 6:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void programExplorationMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Program Exploration and Enrollment Menu ---");
            System.out.println("1. Browse Programs");
            System.out.println("2. Enroll in a Program");
            System.out.println("3. View Program Schedule");
            System.out.println("4. List All Available Programs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:

                    System.out.print("Enter difficulty level (Beginner, Intermediate, Advanced): ");
                    String difficulty = scanner.nextLine();
                    System.out.print("Enter focus area (e.g., Weight Loss, Muscle Building, Flexibility): ");
                    String focusArea = scanner.nextLine();
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();


                    var filteredPrograms = ProgramExplorationAndEnrollment.browsePrograms(difficulty, focusArea, minPrice, maxPrice);
                    ProgramExplorationAndEnrollment.listPrograms(filteredPrograms);
                    break;

                case 2:

                    System.out.print("Enter the program title to enroll in: ");
                    String programTitleToEnroll = scanner.nextLine();
                    ProgramExplorationAndEnrollment.enrollInProgram(programTitleToEnroll);
                    break;

                case 3:

                    System.out.print("Enter the program title to view the schedule: ");
                    String programTitleToViewSchedule = scanner.nextLine();
                    ProgramExplorationAndEnrollment.viewSchedule(programTitleToViewSchedule);
                    break;

                case 4:

                    ProgramExplorationAndEnrollment.listPrograms(ProgramExplorationAndEnrollment.getPrograms());
                    break;

                case 5:

                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void ProgressTrackingClientMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nProgress Tracking Client Menu:");
            System.out.println("1. Update Client Progress");
            System.out.println("2. Show Client Progress");
            System.out.println("3. Add Achievement");
            System.out.println("4. Remove Achievement");
            System.out.println("5. List Achievements");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter client ID: ");
                    String clientId = scanner.nextLine();
                    System.out.print("Enter weight (kg): ");
                    double weight = scanner.nextDouble();
                    System.out.print("Enter height (m): ");
                    double height = scanner.nextDouble();
                    System.out.print("Enter attendance count: ");
                    int attendanceCount = scanner.nextInt();
                    ProgressTrackingClient.updateClientProgress(clientId, weight, height, attendanceCount);
                    break;
                case 2:
                    System.out.print("Enter client ID: ");
                    clientId = scanner.nextLine();
                    ProgressTrackingClient.showClientProgress(clientId);
                    break;
                case 3:
                    System.out.print("Enter client ID: ");
                    clientId = scanner.nextLine();
                    System.out.print("Enter achievement: ");
                    String achievement = scanner.nextLine();
                    System.out.print("Enter achievement date (e.g., 2024-12-20): ");
                    String date = scanner.nextLine();
                    ProgressTrackingClient.addAchievement(clientId, achievement, date);
                    break;
                case 4:
                    System.out.print("Enter client ID: ");
                    clientId = scanner.nextLine();
                    System.out.print("Enter achievement to remove: ");
                    achievement = scanner.nextLine();
                    ProgressTrackingClient.removeAchievement(clientId, achievement);
                    break;
                case 5:
                    System.out.print("Enter client ID: ");
                    clientId = scanner.nextLine();
                    ProgressTrackingClient.listAchievements(clientId);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void FeedbackAndReviewsClientMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nFeedback and Reviews Menu:");
            System.out.println("1. Submit a Program Review");
            System.out.println("2. View Program Reviews");
            System.out.println("3. Submit Improvement Suggestion");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("Enter the program title: ");
                    String programTitle = scanner.nextLine();
                    System.out.print("Enter your rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter your review text: ");
                    String reviewText = scanner.nextLine();
                    System.out.print("Enter your improvement suggestion: ");
                    String improvementSuggestion = scanner.nextLine();


                    FeedbackAndReviews feedbackAndReviews = new FeedbackAndReviews();
                    feedbackAndReviews.submitProgramReview(programTitle, rating, reviewText, improvementSuggestion);
                    break;

                case 2:
                    System.out.print("Enter the program title to view reviews: ");
                    String programToView = scanner.nextLine();
                    FeedbackAndReviews.viewProgramReview(programToView);
                    break;

                case 3:

                    System.out.print("Enter the program title for improvement suggestion: ");
                    String programForSuggestion = scanner.nextLine();
                    System.out.print("Enter the improvement suggestion: ");
                    String newImprovementSuggestion = scanner.nextLine();

                    FeedbackAndReviews.submitImprovementSuggestion(programForSuggestion, newImprovementSuggestion);
                    break;

                case 4:

                    exit = true;
                    System.out.println("Exiting the Feedback and Reviews Menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Adding initial users
        userManagement.addUser("1", "ihab", "Ihab", "kebab83@gmail.com", "ADMIN");
        userManagement.addUser("111", "ameed", "Ameed", "ameed@gmail.com", "INSTRUCTOR");
        userManagement.addUser("11111", "zahi", "Zahi", "zahi@gmail.com", "CLIENT");

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
