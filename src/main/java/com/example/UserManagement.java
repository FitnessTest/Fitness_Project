package com.example;

import java.util.*;

public class UserManagement {

    // User class to store account details
    public static class User {
        public String id;
        public String password;
        String name;
        String email;
        public String role;

        public User(String id, String password, String name, String email, String role) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.role = role;
        }

        public User() {
        }
    }

    private List<User> users;

    public UserManagement(List<User> users) {
        this.users = users; // Use shared user list
    }

    public void createUserAccount(String id, String password, String name, String email, String role) {
        for (User user : users) {
            if (user.id.equals(id)) {
                System.out.println("Account creation failed: ID already exists.");
                return;
            }
        }
        users.add(new User(id, password, name, email, role));
        System.out.println("Account created successfully!");
    }

    public void deleteUserAccount(String id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.id.equals(id)) {
                iterator.remove();
                System.out.println("Account with ID " + id + " deleted successfully.");
                return;
            }
        }
        System.out.println("Account deletion failed: ID not found.");
    }

    public void viewUserAccounts() {
        System.out.println("+-------+-------------------+-------------------+-------------------+------------+");
        System.out.printf("| %-5s | %-17s | %-17s | %-17s | %-10s |%n", "ID", "Name", "Email", "Password", "Role");
        System.out.println("+-------+-------------------+-------------------+-------------------+------------+");
        for (User user : users) {
            System.out.printf("| %-5s | %-17s | %-17s | %-17s | %-10s |%n",
                    user.id, user.name, user.email, user.password, user.role);
        }
        System.out.println("+-------+-------------------+-------------------+-------------------+------------+");
    }
}
