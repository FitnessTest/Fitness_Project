package com.example;

import java.util.*;

public class UserManagement {
    private static List<User> users; // Remove static
    private static StringBuilder logBuilder = new StringBuilder(); // Remove static

    private static final String TABLE_SEPARATOR = "+-------+-------------------+------------+-------------------+---------------+";

    public UserManagement(List<User> users) {
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public String getLogs() {
        return logBuilder.toString();
    }

    public static class User {
        public String id;
        public String password;
        public String name;
        public String email;
        public String role;
        public boolean isActive;
        public boolean isApproved;
        public int engagementStats;

        public User(String id, String password, String name, String email, String role) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.role = role;
            this.isActive = true;
            this.isApproved = !role.equalsIgnoreCase("Instructor");
            this.engagementStats = 0;
        }
    }

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

    public static void deactivateUser(String id) { // Remove static
        for (User user : users) {
            if (user.id.equals(id)) {
                user.isActive = false;
                logBuilder.append("Account with ID ").append(id).append(" has been deactivated.\n");
                return;
            }
        }
        logBuilder.append("Deactivation failed: ID not found. ID: ").append(id).append("\n");
    }

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

    public void viewEngagementStats() {
        logBuilder.append(TABLE_SEPARATOR).append("\n");
        logBuilder.append(String.format("| %-5s | %-17s | %-10s | %-17s | %-13s |\n", "ID", "Name", "Role", "Email", "Engagement"));
        logBuilder.append(TABLE_SEPARATOR).append("\n");
        for (User user : users) {
            logBuilder.append(String.format("| %-5s | %-17s | %-10s | %-17s | %-13d |\n", user.id, user.name, user.role, user.email, user.engagementStats));
        }
        logBuilder.append(TABLE_SEPARATOR).append("\n");
    }

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
