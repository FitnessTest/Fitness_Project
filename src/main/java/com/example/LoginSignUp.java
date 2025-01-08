package com.example;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class LoginSignUp {
    private static final Logger logger = Logger.getLogger(LoginSignUp.class.getName());

    public enum Role {
        ADMIN, INSTRUCTOR, CLIENT
    }

    public static class User extends UserManagement.User {
        public User(String id, String password, String name, String email, String role) {
            super(id, password, name, email, role);
        }
    }
    private static final String CLIENT_ROLE = "CLIENT";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String INSTRUCTOR_ROLE = "INSTRUCTOR";
    private static final String ENTER_CLIENT_ID_PROMPT = "Enter client ID: ";
    private static final String ADD_NEW_CLIENT_OPTION = "1. Add a New Client";
    private static final String BACK_TO_MAIN_MENU = "5. Back to Main Menu";
    private static final String LOGOUT_OPTION = "5. Logout";
    private static final String CHOOSE_OPTION_PROMPT = "Choose an option: ";
    private static final String LOGOUT_MESSAGE = "Logging out...";
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice. Please try again.";
    private static final String ENTER_CHOICE_PROMPT = "Enter your choice: ";
    private static final String RETURN_TO_MAIN_MENU_MESSAGE = "Returning to Main Menu...";

    private static final List<UserManagement.User> users = new ArrayList<>();
    private static final UserManagement userManagement = new UserManagement(users);

    private static boolean isValidID(String id, Role role) {
        if (role == Role.ADMIN) {
            return true;
        } else if (role == Role.INSTRUCTOR) {
            return id.matches("\\d{3}");
        } else if (role == Role.CLIENT) {
            return id.matches("\\d{5}");
        }
        return false;
    }

    private static boolean isUniqueID(String id) {
        return users.stream().noneMatch(user -> user.id.equals(id));
    }
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Optimized regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    public static void signUp(Scanner scanner) {
        logger.info("\n--- Client Signup ---");
        logger.info("Enter ID (5 digits): ");
        String id = scanner.nextLine();
        if (id == null || !isValidID(id, Role.CLIENT)) {
            logger.warning("Invalid ID format. Client IDs must be exactly 5 digits.");
            return;
        }
        if (!isUniqueID(id)) {
            logger.warning("ID already exists. Please try a different ID.");
            return;
        }

        logger.info("Enter Password: ");
        String password = scanner.nextLine();
        if (password == null || password.isEmpty()) {
            logger.warning("Password cannot be empty.");
            return;
        }

        logger.info("Enter Name: ");
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            logger.warning("Name cannot be empty.");
            return;
        }

        logger.info("Enter Email: ");
        String email = scanner.nextLine();
        if (email == null || !isValidEmail(email)) {
            logger.warning("Invalid email format.");
            return;
        }

        try {
            User newUser = new User(id, password, name, email, CLIENT_ROLE);
            users.add(newUser);
            userManagement.addUser(id, password, name, email, CLIENT_ROLE);
            logger.info("Account created successfully! You can now log in.");
        } catch (Exception e) {
            logger.severe("An error occurred while creating the account: " + e.getMessage());
        }
    }

    public static void logInMenu(Scanner scanner) {
        logger.info("\n--- Login Menu ---");
        logger.info("Enter ID: ");
        String id = scanner.nextLine();
        logger.info("Enter Password: ");
        String password = scanner.nextLine();

        boolean loginSuccessful = false;
        for (UserManagement.User user : userManagement.getUsers()) {
            if (user.id.equals(id) && user.password.equals(password)) {
                logger.info(MessageFormat.format("Login successful! Welcome, {0}.", user.role));
                loginSuccessful = true;
                switch (user.role) {
                    case ADMIN_ROLE:
                        adminMenu(scanner);
                        break;
                    case INSTRUCTOR_ROLE:
                        instructorMenu(scanner);
                        break;
                    case CLIENT_ROLE:
                        clientMenu(scanner);
                        break;
                    default:
                        logger.warning("Unknown role.");
                }
                break;
            }
        }

        if (!loginSuccessful) {
            logger.warning("Login failed: Invalid ID or password.");
        }
    }

    public static void adminMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            logger.info("\n--- Admin Menu ---");
            logger.info("1. User Management");
            logger.info("2. Program Monitoring");
            logger.info("3. Content Management");
            logger.info("4. Subscription Management");
            logger.info(LOGOUT_OPTION);
            logger.info(CHOOSE_OPTION_PROMPT);
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
                    logger.info(LOGOUT_MESSAGE);
                    running = false;
                    break;
                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }


    public static void userManagementMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            logger.info("\n--- User Management ---");
            logger.info("1. Create User Account");
            logger.info("2. Update User Account");
            logger.info("3. Deactivate User Account");
            logger.info("4. View User Accounts");
            logger.info("5. Back to Admin Menu");
            logger.info(CHOOSE_OPTION_PROMPT);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logger.info("Enter user ID: ");
                    String id = scanner.nextLine();
                    logger.info("Enter password: ");
                    String password = scanner.nextLine();
                    logger.info("Enter name: ");
                    String name = scanner.nextLine();
                    logger.info("Enter email: ");
                    String email = scanner.nextLine();
                    logger.info("Enter role (Instructor/Client): ");
                    String role = scanner.nextLine();
                    userManagement.addUser(id, password, name, email, role);
                    break;

                case "2":
                    logger.info("Enter user ID to update: ");
                    String updateId = scanner.nextLine();
                    logger.info("Enter new name: ");
                    String newName = scanner.nextLine();
                    logger.info("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    logger.info("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    userManagement.updateUser(updateId, newName, newEmail, newPassword);
                    break;

                case "3":
                    logger.info("Enter user ID to deactivate: ");
                    String deactivateId = scanner.nextLine();
                    UserManagement.deactivateUser(deactivateId);
                    break;

                case "4":
                    userManagement.viewEngagementStats();
                    break;

                case "5":
                    running = false;
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }

    public static void programMonitoringMenu(Scanner scanner) {
        boolean programExit = false;
        while (!programExit) {
            logger.info("\n--- Program Monitoring Menu ---");
            logger.info("1. View Most Popular Programs");
            logger.info("2. Generate Reports");
            logger.info("3. Track Program Status");
            logger.info("4. Back to Admin Menu");
            logger.info(ENTER_CHOICE_PROMPT);

            int programChoice;
            try {
                programChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (Exception e) {
                logger.warning("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (programChoice) {
                case 1:
                    logger.info("Most Popular Programs:");
                    for (String line : ProgramMonitoring.viewMostPopularPrograms()) {
                        logger.info(line);
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
                    logger.info("Returning to Admin Menu...");
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }

    public static void contentManagementMenu(Scanner scanner) {
        boolean running = true;
        ContentManagement cm = new ContentManagement();

        while (running) {
            logger.info("\n--- Content Management ---");
            logger.info("1. Approve or Reject Articles");
            logger.info("2. Handle Feedback and Complaints");
            logger.info("3. View All Content");
            logger.info("4. Back to Admin Menu");
            logger.info(CHOOSE_OPTION_PROMPT);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    approveOrRejectArticles(scanner, cm);
                    break;

                case "2":
                    handleFeedbackAndComplaints(scanner, cm);
                    break;

                case "3":
                    viewAllContent(cm);
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }

    private static void approveOrRejectArticles(Scanner scanner, ContentManagement cm) {
        logger.info("Enter the title of the content to review: ");
        String title = scanner.nextLine();
        logger.info("Approve (yes/no): ");
        String approve = scanner.nextLine().trim().toLowerCase();
        boolean isApproved = "yes".equals(approve);
        cm.approveOrRejectContent(title, isApproved);
    }

    private static void handleFeedbackAndComplaints(Scanner scanner, ContentManagement cm) {
        logger.info("\n--- Handle Feedback and Complaints ---");
        logger.info("1. Add Feedback");
        logger.info("2. Add Complaint");
        logger.info("3. View All Feedback");
        logger.info("4. View All Complaints");
        logger.info(ENTER_CHOICE_PROMPT);
        String subChoice = scanner.nextLine();

        switch (subChoice) {
            case "1":
                addFeedback(scanner, cm);
                break;
            case "2":
                addComplaint(scanner, cm);
                break;
            case "3":
                viewAllFeedback(cm);
                break;
            case "4":
                viewAllComplaints(cm);
                break;
            default:
                logger.warning(INVALID_CHOICE_MESSAGE);
        }
    }

    private static void addFeedback(Scanner scanner, ContentManagement cm) {
        logger.info("Enter feedback: ");
        String feedback = scanner.nextLine();
        cm.handleFeedback(feedback);
    }

    private static void addComplaint(Scanner scanner, ContentManagement cm) {
        logger.info("Enter complaint: ");
        String complaint = scanner.nextLine();
        cm.handleComplaint(complaint);
    }

    private static void viewAllFeedback(ContentManagement cm) {
        logger.info("\nAll Feedback:");
        for (String fb : cm.viewAllFeedback()) {
            logger.info(MessageFormat.format("- {0}", fb));
        }
    }

    private static void viewAllComplaints(ContentManagement cm) {
        logger.info("\nAll Complaints:");
        for (String comp : cm.viewAllComplaints()) {
            logger.info(MessageFormat.format("- {0}", comp));
        }
    }

    private static void viewAllContent(ContentManagement cm) {
        logger.info("\nAll Content:");
        for (String contentStatus : cm.viewAllContent()) {
            logger.info(MessageFormat.format("- {0}", contentStatus));
        }
    }

    public static void subscriptionManagementMenu(Scanner scanner) {
        while (true) {
            logger.info("\n--- Subscription Management ---");
            logger.info("1. View All Subscriptions");
            logger.info("2. Upgrade to Premium");
            logger.info("3. Downgrade to Basic");
            logger.info("4. Change Subscription Plan");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(CHOOSE_OPTION_PROMPT);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    SubscriptionManagement.viewAllSubscriptions();
                    break;

                case "2":
                    logger.info("Enter User ID to upgrade to Premium: ");
                    String upgradeId = scanner.nextLine();
                    SubscriptionManagement.upgradeToPremium(upgradeId);
                    break;

                case "3":
                    logger.info("Enter User ID to downgrade to Basic: ");
                    String downgradeId = scanner.nextLine();
                    SubscriptionManagement.downgradeToBasic(downgradeId);
                    break;

                case "4":
                    logger.info("Enter User ID to change subscription plan: ");
                    String changeId = scanner.nextLine();
                    logger.info("Enter new plan (BASIC or PREMIUM): ");
                    String newPlanStr = scanner.nextLine().toUpperCase();
                    try {
                        SubscriptionManagement.Plan newPlan = SubscriptionManagement.Plan.valueOf(newPlanStr);
                        SubscriptionManagement.changeSubscriptionPlan(changeId, newPlan);
                    } catch (IllegalArgumentException e) {
                        logger.warning("Invalid plan entered. Please enter either BASIC or PREMIUM.");
                    }
                    break;

                case "5":
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    return;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }


    public static void instructorMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            logger.info("\n--- Instructor Menu ---");
            logger.info("1. Program Management");
            logger.info("2. Client Interaction");
            logger.info("3. Progress Tracking");
            logger.info("4. Notifications and Updates");
            logger.info(LOGOUT_OPTION);
            logger.info(CHOOSE_OPTION_PROMPT);
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
                    logger.info(LOGOUT_MESSAGE);
                    break;
                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }

    public static void programManagementMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            try {
                logger.info("\n--- Program Management Menu ---");
                logger.info("1. Create a New Program");
                logger.info("2. Update an Existing Program");
                logger.info("3. Delete a Program");
                logger.info("4. List All Programs");
                logger.info("5. Back to Main Menu");
                logger.info("Enter your choice:");


                int choice;
                while (true) {
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } else {
                        logger.warning("Invalid input, please enter a number.");
                        scanner.nextLine();
                    }
                }

                switch (choice) {
                    case 1:
                        // Create a new program
                        logger.info("Enter details for the new program:");
                        logger.info("Title: ");
                        String title = scanner.nextLine();
                        logger.info("Duration: ");
                        String duration = scanner.nextLine();
                        logger.info("Difficulty Level: ");
                        String difficultyLevel = scanner.nextLine();
                        logger.info("Goals: ");
                        String goals = scanner.nextLine();
                        logger.info("Video Tutorial: ");
                        String videoTutorial = scanner.nextLine();
                        logger.info("Image: ");
                        String image = scanner.nextLine();
                        logger.info("Document: ");
                        String document = scanner.nextLine();

                        // Validate price
                        double price = -1;
                        while (price < 0) {
                            logger.info("Price: ");
                            if (scanner.hasNextDouble()) {
                                price = scanner.nextDouble();
                                scanner.nextLine();
                            } else {
                                logger.warning("Invalid price, please enter a valid number.");
                                scanner.nextLine();
                            }
                        }

                        logger.info("Schedule: ");
                        String schedule = scanner.nextLine();
                        ProgramManagement.createProgram(title, duration, difficultyLevel, goals,
                                videoTutorial, image, document, price, schedule);
                        break;
                    case 2:
                        // Update an existing program
                        logger.info("Enter the title of the program to update: ");
                        String oldTitle = scanner.nextLine();
                        if (!ProgramManagement.programExists(oldTitle)) {
                            logger.warning("Program with title " + oldTitle + " not found.");
                            break;
                        }
                        logger.info("Enter new details for the program:");
                        logger.info("New Title: ");
                        String newTitle = scanner.nextLine();
                        logger.info("New Duration: ");
                        String newDuration = scanner.nextLine();
                        logger.info("New Difficulty Level: ");
                        String newDifficultyLevel = scanner.nextLine();
                        logger.info("New Goals: ");
                        String newGoals = scanner.nextLine();
                        logger.info("New Video Tutorial: ");
                        String newVideoTutorial = scanner.nextLine();
                        logger.info("New Image: ");
                        String newImage = scanner.nextLine();
                        logger.info("New Document: ");
                        String newDocument = scanner.nextLine();


                        double newPrice = -1;
                        while (newPrice < 0) {
                            logger.info("New Price: ");
                            if (scanner.hasNextDouble()) {
                                newPrice = scanner.nextDouble();
                                scanner.nextLine();
                            } else {
                                logger.warning("Invalid price, please enter a valid number.");
                                scanner.nextLine();
                            }
                        }

                        logger.info("New Schedule: ");
                        String newSchedule = scanner.nextLine();
                        ProgramManagement.updateProgram(oldTitle, newTitle, newDuration, newDifficultyLevel,
                                newGoals, newVideoTutorial, newImage, newDocument, newPrice, newSchedule);
                        break;
                    case 3:
                        // Delete a program
                        logger.info("Enter the title of the program to delete: ");
                        String deleteTitle = scanner.nextLine();

                        if (!ProgramManagement.programExists(deleteTitle)) {
                            logger.warning(String.format("Program with title \"%s\" not found.", deleteTitle));
                            break;
                        }
                        ProgramManagement.deleteProgram(deleteTitle);
                        break;
                    case 4:

                        ProgramManagement.listAllPrograms();
                        break;
                    case 5:

                        exit = true;
                        logger.info("Returning to Main Menu.");
                        break;
                    default:
                        logger.warning("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (Exception e) {
                logger.warning("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public static void clientInteractionMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Client Interaction Menu ---");
            logger.info(ADD_NEW_CLIENT_OPTION);
            logger.info("2. Send Message to a Client");
            logger.info("3. Provide Progress Report to a Client");
            logger.info("4. List All Clients");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logger.info("Enter details for the new client:");
                    logger.info("Name: ");
                    String name = scanner.nextLine();
                    logger.info("Email: ");
                    String email = scanner.nextLine();
                    ClientInteraction.addClient(name, email);
                    break;

                case 2:
                    logger.info("Enter the email of the client to send a message: ");
                    String clientEmailToMessage = scanner.nextLine();
                    logger.info("Enter the message: ");
                    String message = scanner.nextLine();
                    ClientInteraction.sendMessageToClient(clientEmailToMessage, message);
                    break;

                case 3:
                    logger.info("Enter the email of the client to send a progress report: ");
                    String clientEmailForReport = scanner.nextLine();
                    logger.info("Enter the progress report: ");
                    String report = scanner.nextLine();
                    ClientInteraction.provideProgressReport(clientEmailForReport, report);
                    break;

                case 4:
                    ClientInteraction.listAllClients();
                    break;

                case 5:
                    exit = true;
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }

    public static void progressTrackingMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Progress Tracking Menu ---");
            logger.info(ADD_NEW_CLIENT_OPTION);
            logger.info("2. Update Completed Sessions for a Client");
            logger.info("3. Send Motivational Reminder to a Client");
            logger.info("4. Monitor All Client Progress");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logger.info("Enter details for the new client:");
                    logger.info("Name: ");
                    String name = scanner.nextLine();
                    logger.info("Email: ");
                    String email = scanner.nextLine();
                    logger.info("Total Sessions: ");
                    int totalSessions = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        ProgressTracking.addClient(new ProgressTracking.Client(name, email, totalSessions));
                        logger.info("Client added successfully.");
                    } catch (IllegalArgumentException e) {
                        logger.warning("Error adding client: " + e.getMessage());
                    }
                    break;

                case 2:
                    logger.info("Enter the email of the client: ");
                    String clientEmailToUpdate = scanner.nextLine();
                    logger.info("Enter the number of completed sessions: ");
                    int sessionsCompleted = scanner.nextInt();
                    scanner.nextLine();

                    ProgressTracking.updateCompletedSessions(clientEmailToUpdate, sessionsCompleted);
                    break;

                case 3:
                    logger.info("Enter the email of the client to send a reminder: ");
                    String clientEmailToMessage = scanner.nextLine();
                    logger.info("Enter the motivational message: ");
                    String message = scanner.nextLine();

                    if (ProgressTracking.sendMotivationalReminder(clientEmailToMessage, message)) {
                        logger.info("Motivational reminder sent successfully.");
                    } else {
                        logger.info("Client not found.");
                    }
                    break;

                case 4:
                    ProgressTracking.monitorClientProgress();
                    break;

                case 5:
                    exit = true;
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }

    public static void notificationsAndUpdatesMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Notifications and Updates Menu ---");
            logger.info(ADD_NEW_CLIENT_OPTION);
            logger.info("2. Notify Schedule Change");
            logger.info("3. Announce New Program or Offer");
            logger.info("4. List All Clients and Notifications");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    logger.info("Enter Client Name: ");
                    String name = scanner.nextLine();
                    logger.info("Enter Client Email: ");
                    String email = scanner.nextLine();
                    NotificationsAndUpdates.addClient(name, email);
                    break;

                case 2:
                    logger.info("Enter Program Title: ");
                    String programTitle = scanner.nextLine();
                    logger.info("Enter New Schedule: ");
                    String newSchedule = scanner.nextLine();
                    NotificationsAndUpdates.notifyScheduleChange(programTitle, newSchedule);
                    break;

                case 3:
                    logger.info("Enter Announcement: ");
                    String announcement = scanner.nextLine();
                    NotificationsAndUpdates.announceNewProgramOrOffer(announcement);
                    break;

                case 4:
                    NotificationsAndUpdates.listAllClientsNotifications();
                    break;

                case 5:
                    exit = true;
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }


    // Client menu
    public static void clientMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            logger.info("\n--- Client Menu ---");
            logger.info("1. Account Management");
            logger.info("2. Program Exploration and Enrollment");
            logger.info("3. Progress Tracking");
            logger.info("4. Feedback and Reviews");
            logger.info(LOGOUT_OPTION);
            logger.info(CHOOSE_OPTION_PROMPT);
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
                    logger.info(LOGOUT_MESSAGE);
                    break;
                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }

    public static void accountManagementMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Account Management Menu ---");
            logger.info("1. Create Profile");
            logger.info("2. Update Profile");
            logger.info("3. View Profile");
            logger.info("4. Delete Profile");
            logger.info("5. List All Profiles");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logger.info("Enter Name: ");
                    String name = scanner.nextLine();
                    logger.info("Enter Email: ");
                    String email = scanner.nextLine();
                    logger.info("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    logger.info("Enter Fitness Goals: ");
                    String fitnessGoals = scanner.nextLine();
                    logger.info("Enter Dietary Preferences: ");
                    String dietaryPreferences = scanner.nextLine();
                    AccountManagement.createProfile(name, email, age, fitnessGoals, dietaryPreferences);
                    break;

                case 2:
                    logger.info("Enter Email of Profile to Update: ");
                    String emailToUpdate = scanner.nextLine();
                    logger.info("Enter New Name: ");
                    String newName = scanner.nextLine();
                    logger.info("Enter New Age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    logger.info("Enter New Fitness Goals: ");
                    String newFitnessGoals = scanner.nextLine();
                    logger.info("Enter New Dietary Preferences: ");
                    String newDietaryPreferences = scanner.nextLine();
                    AccountManagement.updateProfile(emailToUpdate, newName, newAge, newFitnessGoals, newDietaryPreferences);
                    break;

                case 3:
                    logger.info("Enter Email to View Profile: ");
                    String emailToView = scanner.nextLine();
                    AccountManagement.ClientProfile profile = AccountManagement.viewProfile(emailToView);
                    if (profile != null) {
                        logger.info("Profile Details:");
                        logger.info(profile.toString());
                    }
                    break;

                case 4:
                    logger.info("Enter Email to Delete Profile: ");
                    String emailToDelete = scanner.nextLine();
                    AccountManagement.deleteProfile(emailToDelete);
                    break;

                case 5:
                    AccountManagement.listAllProfiles();
                    break;

                case 6:
                    exit = true;
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }

    public static void programExplorationMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Program Exploration and Enrollment Menu ---");
            logger.info("1. Browse Programs");
            logger.info("2. Enroll in a Program");
            logger.info("3. View Program Schedule");
            logger.info("4. List All Available Programs");
            logger.info(BACK_TO_MAIN_MENU);
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    logger.info("Enter difficulty level (Beginner, Intermediate, Advanced): ");
                    String difficulty = scanner.nextLine();
                    logger.info("Enter focus area (e.g., Weight Loss, Muscle Building, Flexibility): ");
                    String focusArea = scanner.nextLine();
                    logger.info("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    logger.info("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();

                    var filteredPrograms = ProgramExplorationAndEnrollment.browsePrograms(difficulty, focusArea, minPrice, maxPrice);
                    ProgramExplorationAndEnrollment.listPrograms(filteredPrograms);
                    break;

                case 2:
                    logger.info("Enter the program title to enroll in: ");
                    String programTitleToEnroll = scanner.nextLine();
                    ProgramExplorationAndEnrollment.enrollInProgram(programTitleToEnroll);
                    break;

                case 3:
                    logger.info("Enter the program title to view the schedule: ");
                    String programTitleToViewSchedule = scanner.nextLine();
                    ProgramExplorationAndEnrollment.viewSchedule(programTitleToViewSchedule);
                    break;

                case 4:
                    ProgramExplorationAndEnrollment.listPrograms(ProgramExplorationAndEnrollment.getPrograms());
                    break;

                case 5:
                    exit = true;
                    logger.info(RETURN_TO_MAIN_MENU_MESSAGE);
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }

    public static void ProgressTrackingClientMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\nProgress Tracking Client Menu:");
            logger.info("1. Update Client Progress");
            logger.info("2. Show Client Progress");
            logger.info("3. Add Achievement");
            logger.info("4. Remove Achievement");
            logger.info("5. List Achievements");
            logger.info("6. Exit");
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logger.info(ENTER_CLIENT_ID_PROMPT);
                    String clientId = scanner.nextLine();
                    logger.info("Enter weight (kg): ");
                    double weight = scanner.nextDouble();
                    logger.info("Enter height (m): ");
                    double height = scanner.nextDouble();
                    logger.info("Enter attendance count: ");
                    int attendanceCount = scanner.nextInt();
                    ProgressTrackingClient.updateClientProgress(clientId, weight, height, attendanceCount);
                    break;
                case 2:
                    logger.info(ENTER_CLIENT_ID_PROMPT);
                    clientId = scanner.nextLine();
                    ProgressTrackingClient.showClientProgress(clientId);
                    break;
                case 3:
                    logger.info(ENTER_CLIENT_ID_PROMPT);
                    clientId = scanner.nextLine();
                    logger.info("Enter achievement: ");
                    String achievement = scanner.nextLine();
                    logger.info("Enter achievement date (e.g., 2024-12-20): ");
                    String date = scanner.nextLine();
                    ProgressTrackingClient.addAchievement(clientId, achievement, date);
                    break;
                case 4:
                    logger.info(ENTER_CLIENT_ID_PROMPT);
                    clientId = scanner.nextLine();
                    logger.info("Enter achievement to remove: ");
                    achievement = scanner.nextLine();
                    ProgressTrackingClient.removeAchievement(clientId, achievement);
                    break;
                case 5:
                    logger.info(ENTER_CLIENT_ID_PROMPT);
                    clientId = scanner.nextLine();
                    ProgressTrackingClient.listAchievements(clientId);
                    break;
                case 6:
                    exit = true;
                    logger.info("Exiting the menu.");
                    break;
                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }
    }

    public static void FeedbackAndReviewsClientMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            logger.info("\nFeedback and Reviews Menu:");
            logger.info("1. Submit a Program Review");
            logger.info("2. View Program Reviews");
            logger.info("3. Submit Improvement Suggestion");
            logger.info("4. Exit");
            logger.info(ENTER_CHOICE_PROMPT);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logger.info("Enter the program title: ");
                    String programTitle = scanner.nextLine();
                    logger.info("Enter your rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    logger.info("Enter your review text: ");
                    String reviewText = scanner.nextLine();
                    logger.info("Enter your improvement suggestion: ");
                    String improvementSuggestion = scanner.nextLine();

                    FeedbackAndReviews.submitProgramReview(programTitle, rating, reviewText, improvementSuggestion);
                    break;

                case 2:
                    logger.info("Enter the program title to view reviews: ");
                    String programToView = scanner.nextLine();
                    FeedbackAndReviews.viewProgramReview(programToView);
                    break;

                case 3:
                    logger.info("Enter the program title for improvement suggestion: ");
                    String programForSuggestion = scanner.nextLine();
                    logger.info("Enter the improvement suggestion: ");
                    String newImprovementSuggestion = scanner.nextLine();

                    FeedbackAndReviews.submitImprovementSuggestion(programForSuggestion, newImprovementSuggestion);
                    break;

                case 4:
                    exit = true;
                    logger.info("Exiting the Feedback and Reviews Menu.");
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
            }
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        userManagement.addUser("1", "ihab", "Ihab", "kebab83@gmail.com", ADMIN_ROLE);
        userManagement.addUser("111", "ameed", "Ameed", "ameed@gmail.com", INSTRUCTOR_ROLE);
        userManagement.addUser("11111", "zahi", "Zahi", "zahi@gmail.com", CLIENT_ROLE);

        while (running) {
            logger.info("\n--- Login/Signup System ---");
            logger.info("1. Login");
            logger.info("2. Sign Up (Clients Only)");
            logger.info("3. Exit");
            logger.info(CHOOSE_OPTION_PROMPT);
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
                    logger.info("Exiting... Goodbye!");
                    break;

                default:
                    logger.warning(INVALID_CHOICE_MESSAGE);
                    break;
            }
        }

        scanner.close();
    }
}