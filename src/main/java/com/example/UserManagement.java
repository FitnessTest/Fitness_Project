package com.example;

import java.util.*;

/**
 * Manages user accounts, including adding, updating, deactivating, and viewing users.
 * It also handles user roles, activity stats, and logs.
 */
public class UserManagement {
    private static List<User> users; // List to store users
    private static StringBuilder logBuilder = new StringBuilder(); // Logs for actions performed

    private static final String TABLE_SEPARATOR = "+-------+-------------------+------------+-------------------+---------------+";

    /**
     * Constructor to initialize the UserManagement with a list of users.
     *
     * @param users List of users to initialize the management system
     */
    public UserManagement(List<User> users) {
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
    }

    /**
     * Retrieves the list of all users.
     *
     * @return List of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Retrieves the log of actions performed.
     *
     * @return Log of actions
     */
    public String getLogs() {
        return logBuilder.toString();
    }

    /**
     * Represents a User with all relevant details such as ID, password, name, role, etc.
     */
    public static class User {
        /** User ID */
        public String id;

        /** User password */
        public String password;

        /** User's name */
        public String name;

        /** User's email address */
        public String email;

        /** User's role (e.g., Client, Instructor) */
        public String role;

        /** Indicates if the user's account is active */
        public boolean isActive;

        /** Indicates if the user has been approved (only relevant for Instructors) */
        public boolean isApproved;

        /** Engagement statistics for the user */
        public int engagementStats;

        /**
         * Constructor to create a new user.
         *
         * @param id User ID
         * @param password User password
         * @param name User's name
         * @param email User's email
         * @param role User's role (e.g., Client, Instructor)
         */
        public User(String id, String password, String name, String email, String role) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.role = role;
            this.isActive = true; // Active by default
            this.isApproved = !role.equalsIgnoreCase("Instructor"); // Approve non-Instructors by default
            this.engagementStats = 0; // Default engagement is zero
        }
    }
    /**
     * Adds a new user to the system. If the ID already exists, the user is not added.
     *
     * @param id User ID
     * @param password User password
     * @param name User's name
     * @param email User's email
     * @param role User's role
     */
    public void addUser(String id, String password, String name, String email, String role) {
        for (User user : users) {
            if (user.id.equals(id)) {
                logBuilder.append("Account creation failed: ID already exists.\n");
                return;
            }
        }
        users.add(new User(id, password, name, email, role));
        logBuilder.append("Account created successfully! ID: ").append(id).append("\n");
    }

    /**
     * Updates an existing user's information.
     *
     * @param id User ID
     * @param newName New name for the user
     * @param newEmail New email for the user
     * @param newPassword New password for the user
     */
    public void updateUser(String id, String newName, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.name = newName;
                user.email = newEmail;
                user.password = newPassword;
                logBuilder.append("Account updated successfully! ID: ").append(id).append("\n");
                return;
            }
        }
        logBuilder.append("Update failed: ID not found. ID: ").append(id).append("\n");
    }

    /**
     * Deactivates a user's account.
     *
     * @param id User ID
     */
    public static void deactivateUser(String id) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.isActive = false;
                logBuilder.append("Account with ID ").append(id).append(" has been deactivated.\n");
                return;
            }
        }
        logBuilder.append("Deactivation failed: ID not found. ID: ").append(id).append("\n");
    }

    /**
     * Approves an Instructor for a role.
     *
     * @param id User ID
     */
    public void approveInstructor(String id) {
        for (User user : users) {
            if (user.id.equals(id) && user.role.equalsIgnoreCase("Instructor")) {
                if (user.isApproved) {
                    logBuilder.append("Instructor with ID ").append(id).append(" is already approved.\n");
                } else {
                    user.isApproved = true;
                    logBuilder.append("Instructor with ID ").append(id).append(" approved successfully.\n");
                }
                return;
            }
        }
        logBuilder.append("Approval failed: Instructor ID not found or not an instructor. ID: ").append(id).append("\n");
    }

    /**
     * Views the engagement statistics of all users in a tabular format.
     */
    public void viewEngagementStats() {
        logBuilder.append(TABLE_SEPARATOR).append("\n");
        logBuilder.append(String.format("| %-5s | %-17s | %-10s | %-17s | %-13s |\n", "ID", "Name", "Role", "Email", "Engagement"));
        logBuilder.append(TABLE_SEPARATOR).append("\n");
        for (User user : users) {
            logBuilder.append(String.format("| %-5s | %-17s | %-10s | %-17s | %-13d |\n", user.id, user.name, user.role, user.email, user.engagementStats));
        }
        logBuilder.append(TABLE_SEPARATOR).append("\n");
    }

    /**
     * Simulates activity for a user by updating their engagement stats.
     *
     * @param id User ID
     * @param additionalEngagement The amount to increase the engagement stats by
     */
    public void simulateActivity(String id, int additionalEngagement) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.engagementStats += additionalEngagement;
                logBuilder.append("Engagement stats updated for user ID ").append(id).append(". New Engagement: ").append(user.engagementStats).append("\n");
                return;
            }
        }
        logBuilder.append("Activity simulation failed: ID not found. ID: ").append(id).append("\n");
    }
}
