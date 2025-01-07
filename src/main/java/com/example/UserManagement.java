package com.example;

import java.util.*;
import java.util.logging.Logger;

public class UserManagement {

    private static final Logger logger = Logger.getLogger(UserManagement.class.getName());

    private static List<User> users;

    public UserManagement(List<User> users) {
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
    }

    public Iterable<? extends User> getUsers() {
        return users;
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
                logger.warning("Account creation failed: ID already exists.");
                return;
            }
        }
        users.add(new User(id, password, name, email, role));
        logger.info("Account created successfully!");
    }

    public void updateUser(String id, String newName, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.name = newName;
                user.email = newEmail;
                user.password = newPassword;
                logger.info("Account updated successfully!");
                return;
            }
        }
        logger.warning("Update failed: ID not found.");
    }

    public static void deactivateUser(String id) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.isActive = false;
                logger.info("Account with ID " + id + " has been deactivated.");
                return;
            }
        }
        logger.warning("Deactivation failed: ID not found.");
    }

    public void approveInstructor(String id) {
        for (User user : users) {
            if (user.id.equals(id) && user.role.equalsIgnoreCase("Instructor")) {
                if (user.isApproved) {
                    logger.info("Instructor with ID " + id + " is already approved.");
                } else {
                    user.isApproved = true;
                    logger.info("Instructor with ID " + id + " approved successfully.");
                }
                return;
            }
        }
        logger.warning("Approval failed: Instructor ID not found or not an instructor.");
    }

    public void viewEngagementStats() {
        logger.info("+-------+-------------------+------------+-------------------+---------------+");
        logger.info(String.format("| %-5s | %-17s | %-10s | %-17s | %-13s |", "ID", "Name", "Role", "Email", "Engagement"));
        logger.info("+-------+-------------------+------------+-------------------+---------------+");
        for (User user : users) {
            logger.info(String.format("| %-5s | %-17s | %-10s | %-17s | %-13d |", user.id, user.name, user.role, user.email, user.engagementStats));
        }
        logger.info("+-------+-------------------+------------+-------------------+---------------+");
    }

    public void simulateActivity(String id, int additionalEngagement) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.engagementStats += additionalEngagement;
                logger.info("Engagement stats updated for user ID " + id + ".");
                return;
            }
        }
        logger.warning("Activity simulation failed: ID not found.");
    }
}