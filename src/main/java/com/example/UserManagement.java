package com.example;

import java.util.*;

public class UserManagement {


    public Iterable<? extends User> getUsers() {
        return  users;
    }

    public static class User {
        public String id;
        public String password;
        String name;
        String email;
        public String role; // "Instructor" or "Client"
        boolean isActive;
        boolean isApproved; // For instructors only
        int engagementStats; // Engagement statistics

        public User(String id, String password, String name, String email, String role) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.role = role;
            this.isActive = true; // Accounts are active by default
            this.isApproved = !role.equalsIgnoreCase("Instructor"); // Only instructors need approval
            this.engagementStats = 0; // Initial engagement stats
        }
    }

    private List<User> users;

    public UserManagement(List<User> users) {
        this.users = new ArrayList<>();
    }


    public void addUser(String id, String password, String name, String email, String role) {
        for (User user : users) {
            if (user.id.equals(id)) {
                System.out.println("Account creation failed: ID already exists.");
                return;
            }
        }
        users.add(new User(id, password, name, email, role));
        System.out.println("Account created successfully!");
    }


    public void updateUser(String id, String newName, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.name = newName;
                user.email = newEmail;
                user.password = newPassword;
                System.out.println("Account updated successfully!");
                return;
            }
        }
        System.out.println("Update failed: ID not found.");
    }


    public void deactivateUser(String id) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.isActive = false;
                System.out.println("Account with ID " + id + " has been deactivated.");
                return;
            }
        }
        System.out.println("Deactivation failed: ID not found.");
    }


    public void approveInstructor(String id) {
        for (User user : users) {
            if (user.id.equals(id) && user.role.equalsIgnoreCase("Instructor")) {
                if (user.isApproved) {
                    System.out.println("Instructor with ID " + id + " is already approved.");
                } else {
                    user.isApproved = true;
                    System.out.println("Instructor with ID " + id + " approved successfully.");
                }
                return;
            }
        }
        System.out.println("Approval failed: Instructor ID not found or not an instructor.");
    }


    public void viewEngagementStats() {
        System.out.println("+-------+-------------------+------------+-------------------+---------------+");
        System.out.printf("| %-5s | %-17s | %-10s | %-17s | %-13s |%n", "ID", "Name", "Role", "Email", "Engagement");
        System.out.println("+-------+-------------------+------------+-------------------+---------------+");
        for (User user : users) {
            System.out.printf("| %-5s | %-17s | %-10s | %-17s | %-13d |%n",
                    user.id, user.name, user.role, user.email, user.engagementStats);
        }
        System.out.println("+-------+-------------------+------------+-------------------+---------------+");
    }


    public void simulateActivity(String id, int additionalEngagement) {
        for (User user : users) {
            if (user.id.equals(id)) {
                user.engagementStats += additionalEngagement;
                System.out.println("Engagement stats updated for user ID " + id + ".");
                return;
            }
        }
        System.out.println("Activity simulation failed: ID not found.");
    }
}
