/*package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize instances of all modules
        AccountManagement accountManagement = new AccountManagement();
        ClientInteraction clientInteraction = new ClientInteraction();
        ContentManagement contentManagement = new ContentManagement();
        FeedbackAndReviews feedbackAndReviews = new FeedbackAndReviews();
        NotificationsAndUpdates notificationsAndUpdates = new NotificationsAndUpdates();
        ProgramExplorationAndEnrollment programExploration = new ProgramExplorationAndEnrollment();
        ProgramManagement programManagement = new ProgramManagement();
        ProgramMonitoring programMonitoring = new ProgramMonitoring();
        ProgressTracking progressTracking = new ProgressTracking();
        SubscriptionManagement subscriptionManagement = new SubscriptionManagement();
        UserManagement userManagement = new UserManagement();

        while (true) {
            System.out.println("\n--- Fitness Management System ---");
            System.out.println("1. Manage Accounts");
            System.out.println("2. Client Interaction");
            System.out.println("3. Content Management");
            System.out.println("4. Feedback and Reviews");
            System.out.println("5. Notifications and Updates");
            System.out.println("6. Program Exploration and Enrollment");
            System.out.println("7. Program Management");
            System.out.println("8. Program Monitoring");
            System.out.println("9. Progress Tracking");
            System.out.println("10. Subscription Management");
            System.out.println("11. User Management");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Manage Accounts:");
                    while (true) {
                        System.out.println("\n1. Create Profile");
                        System.out.println("2. Update Profile");
                        System.out.println("3. View Profile");
                        System.out.println("4. Delete Profile");
                        System.out.println("5. List All Profiles");
                        System.out.println("6. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int accountChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (accountChoice == 6) break;

                        switch (accountChoice) {
                            case 1:
                                System.out.print("Enter name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter email: ");
                                String email = scanner.nextLine();
                                System.out.print("Enter age: ");
                                int age = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter fitness goals: ");
                                String fitnessGoals = scanner.nextLine();
                                System.out.print("Enter dietary preferences: ");
                                String dietaryPreferences = scanner.nextLine();
                                accountManagement.createProfile(name, email, age, fitnessGoals, dietaryPreferences);
                                break;
                            case 2:
                                System.out.print("Enter email: ");
                                String updateEmail = scanner.nextLine();
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new age: ");
                                int newAge = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter new fitness goals: ");
                                String newFitnessGoals = scanner.nextLine();
                                System.out.print("Enter new dietary preferences: ");
                                String newDietaryPreferences = scanner.nextLine();
                                accountManagement.updateProfile(updateEmail, newName, newAge, newFitnessGoals, newDietaryPreferences);
                                break;
                            case 3:
                                System.out.print("Enter email to view profile: ");
                                String viewEmail = scanner.nextLine();
                                System.out.println(accountManagement.viewProfile(viewEmail));
                                break;
                            case 4:
                                System.out.print("Enter email to delete profile: ");
                                String deleteEmail = scanner.nextLine();
                                accountManagement.deleteProfile(deleteEmail);
                                break;
                            case 5:
                                System.out.println("Listing all profiles:");
                                accountManagement.listAllProfiles();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Client Interaction:");
                    while (true) {
                        System.out.println("\n1. Add Client");
                        System.out.println("2. Send Message to Client");
                        System.out.println("3. Provide Progress Report");
                        System.out.println("4. List All Clients");
                        System.out.println("5. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int clientChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (clientChoice == 5) break;

                        switch (clientChoice) {
                            case 1:
                                System.out.print("Enter client name: ");
                                String clientName = scanner.nextLine();
                                System.out.print("Enter client email: ");
                                String clientEmail = scanner.nextLine();
                                clientInteraction.addClient(clientName, clientEmail);
                                break;
                            case 2:
                                System.out.print("Enter client email: ");
                                String messageEmail = scanner.nextLine();
                                System.out.print("Enter message: ");
                                String message = scanner.nextLine();
                                clientInteraction.sendMessageToClient(messageEmail, message);
                                break;
                            case 3:
                                System.out.print("Enter client email: ");
                                String reportEmail = scanner.nextLine();
                                System.out.print("Enter progress report: ");
                                String report = scanner.nextLine();
                                clientInteraction.provideProgressReport(reportEmail, report);
                                break;
                            case 4:
                                System.out.println("Listing all clients:");
                                clientInteraction.listAllClients();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Content Management:");
                    while (true) {
                        System.out.println("\n1. Approve or Reject Content");
                        System.out.println("2. View All Content");
                        System.out.println("3. Handle Feedback");
                        System.out.println("4. Handle Complaint");
                        System.out.println("5. View All Feedback");
                        System.out.println("6. View All Complaints");
                        System.out.println("7. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int contentChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (contentChoice == 7) break;

                        switch (contentChoice) {
                            case 1:
                                System.out.print("Enter content title: ");
                                String title = scanner.nextLine();
                                System.out.print("Approve content? (true/false): ");
                                boolean approve = scanner.nextBoolean();
                                scanner.nextLine(); // Consume newline
                                contentManagement.approveOrRejectContent(title, approve);
                                break;
                            case 2:
                                System.out.println("Viewing all content:");
                                System.out.println(contentManagement.viewAllContent());
                                break;
                            case 3:
                                System.out.print("Enter feedback: ");
                                String feedback = scanner.nextLine();
                                contentManagement.handleFeedback(feedback);
                                break;
                            case 4:
                                System.out.print("Enter complaint: ");
                                String complaint = scanner.nextLine();
                                contentManagement.handleComplaint(complaint);
                                break;
                            case 5:
                                System.out.println("Viewing all feedback:");
                                System.out.println(contentManagement.viewAllFeedback());
                                break;
                            case 6:
                                System.out.println("Viewing all complaints:");
                                System.out.println(contentManagement.viewAllComplaints());
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Feedback and Reviews:");
                    while (true) {
                        System.out.println("\n--- Feedback and Reviews Menu ---");
                        System.out.println("1. Submit Program Review");
                        System.out.println("2. View Program Review");
                        System.out.println("3. Submit Improvement Suggestion");
                        System.out.println("4. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int feedbackChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (feedbackChoice == 4) break;

                        switch (feedbackChoice) {
                            case 1: // Submit Program Review
                                System.out.print("Enter program title: ");
                                String programTitle = scanner.nextLine();
                                System.out.print("Enter rating (1-5): ");
                                int rating = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter review text: ");
                                String reviewText = scanner.nextLine();
                                System.out.print("Enter improvement suggestion: ");
                                String improvementSuggestion = scanner.nextLine();

                                feedbackAndReviews.submitProgramReview(programTitle, rating, reviewText, improvementSuggestion);
                                break;

                            case 2: // View Program Review
                                System.out.print("Enter program title: ");
                                String viewTitle = scanner.nextLine();

                                String reviews = feedbackAndReviews.viewProgramReview(viewTitle);
                                System.out.println("Reviews for " + viewTitle + ":");
                                System.out.println(reviews);
                                break;

                            case 3: // Submit Improvement Suggestion
                                System.out.print("Enter program title: ");
                                String suggestionTitle = scanner.nextLine();
                                System.out.print("Enter improvement suggestion: ");
                                String suggestionText = scanner.nextLine();

                                feedbackAndReviews.submitImprovementSuggestion(suggestionTitle, suggestionText);
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Notifications and Updates:");
                    while (true) {
                        System.out.println("\n1. Add Client");
                        System.out.println("2. Notify Schedule Change");
                        System.out.println("3. Announce New Program or Offer");
                        System.out.println("4. List All Clients' Notifications");
                        System.out.println("5. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int notificationChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (notificationChoice == 5) break;

                        switch (notificationChoice) {
                            case 1:
                                System.out.print("Enter client name: ");
                                String clientName = scanner.nextLine();
                                System.out.print("Enter client email: ");
                                String clientEmail = scanner.nextLine();
                                notificationsAndUpdates.addClient(clientName, clientEmail);
                                break;
                            case 2:
                                System.out.print("Enter program title: ");
                                String programTitle = scanner.nextLine();
                                System.out.print("Enter new schedule: ");
                                String newSchedule = scanner.nextLine();
                                notificationsAndUpdates.notifyScheduleChange(programTitle, newSchedule);
                                break;
                            case 3:
                                System.out.print("Enter announcement: ");
                                String announcement = scanner.nextLine();
                                notificationsAndUpdates.announceNewProgramOrOffer(announcement);
                                break;
                            case 4:
                                System.out.println("Listing all clients' notifications:");
                                notificationsAndUpdates.listAllClientsNotifications();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Program Exploration and Enrollment:");
                    while (true) {
                        System.out.println("\n1. List All Programs");
                        System.out.println("2. Enroll in a Program");
                        System.out.println("3. View Program Schedule");
                        System.out.println("4. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int programChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (programChoice == 4) break;

                        switch (programChoice) {
                            case 1:
                                System.out.println("Available Programs:");
                                programExploration.listPrograms(programExploration.browsePrograms(null, null, 0, Double.MAX_VALUE));
                                break;
                            case 2:
                                System.out.print("Enter program title to enroll: ");
                                String enrollTitle = scanner.nextLine();
                                programExploration.enrollInProgram(enrollTitle);
                                break;
                            case 3:
                                System.out.print("Enter program title to view schedule: ");
                                String scheduleTitle = scanner.nextLine();
                                programExploration.viewSchedule(scheduleTitle);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Program Management:");
                    while (true) {
                        System.out.println("\n1. Create Program");
                        System.out.println("2. Update Program");
                        System.out.println("3. Delete Program");
                        System.out.println("4. List All Programs");
                        System.out.println("5. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        int managementChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (managementChoice == 5) break;

                        switch (managementChoice) {
                            case 1:
                                System.out.print("Enter program title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter duration: ");
                                String duration = scanner.nextLine();
                                System.out.print("Enter difficulty level: ");
                                String difficultyLevel = scanner.nextLine();
                                System.out.print("Enter goals: ");
                                String goals = scanner.nextLine();
                                System.out.print("Enter video tutorial link: ");
                                String videoTutorial = scanner.nextLine();
                                System.out.print("Enter image link: ");
                                String image = scanner.nextLine();
                                System.out.print("Enter document link: ");
                                String document = scanner.nextLine();
                                System.out.print("Enter price: ");
                                double price = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter schedule: ");
                                String schedule = scanner.nextLine();
                                programManagement.createProgram(title, duration, difficultyLevel, goals, videoTutorial, image, document, price, schedule);
                                break;
                            case 2:
                                System.out.print("Enter old program title: ");
                                String oldTitle = scanner.nextLine();
                                System.out.print("Enter new title: ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Enter new duration: ");
                                String newDuration = scanner.nextLine();
                                System.out.print("Enter new difficulty level: ");
                                String newDifficultyLevel = scanner.nextLine();
                                System.out.print("Enter new goals: ");
                                String newGoals = scanner.nextLine();
                                System.out.print("Enter new video tutorial link: ");
                                String newVideoTutorial = scanner.nextLine();
                                System.out.print("Enter new image link: ");
                                String newImage = scanner.nextLine();
                                System.out.print("Enter new document link: ");
                                String newDocument = scanner.nextLine();
                                System.out.print("Enter new price: ");
                                double newPrice = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter new schedule: ");
                                String newSchedule = scanner.nextLine();
                                programManagement.updateProgram(oldTitle, newTitle, newDuration, newDifficultyLevel, newGoals, newVideoTutorial, newImage, newDocument, newPrice, newSchedule);
                                break;
                            case 3:
                                System.out.print("Enter program title to delete: ");
                                String deleteTitle = scanner.nextLine();
                                boolean deleted = programManagement.deleteProgram(deleteTitle);
                                if (deleted) {
                                    System.out.println("Program deleted successfully.");
                                } else {
                                    System.out.println("Program not found.");
                                }
                                break;
                            case 4:
                                System.out.println("Listing all programs:");
                                programManagement.listAllPrograms();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 8:
                    System.out.println("Program Monitoring:");
                    programMonitoring.monitorProgram();
                    break;
                case 9:
                    System.out.println("Progress Tracking:");
                    progressTracking.trackProgress();
                    progressTracking.viewProgress();
                    break;
                case 10:
                    System.out.println("Subscription Management:");
                    subscriptionManagement.addSubscription();
                    subscriptionManagement.viewSubscriptions();
                    break;
                case 11:
                    System.out.println("User Management:");
                    userManagement.createUser();
                    userManagement.deleteUser();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}*/
