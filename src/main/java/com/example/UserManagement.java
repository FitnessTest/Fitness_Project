package com.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement {

    private List<String> userAccounts;
    private boolean accountCreationSuccess;
    private boolean accountDeletionSuccess;
    private Map<String, String> userAccountsMap;


    public UserManagement() {
        userAccounts = new ArrayList<>();
        userAccountsMap = new HashMap<>();


        userAccountsMap.put("amrojamhour4@gmail.com", "Instructor");
        userAccountsMap.put("ameed@gmail.com", "Client");
        userAccountsMap.put("zahi.q83@gmail.com", "Instructor");


        refreshUserAccounts();
    }


    private void refreshUserAccounts() {
        userAccounts.clear();
        for (Map.Entry<String, String> entry : userAccountsMap.entrySet()) {
            userAccounts.add(entry.getValue() + ": " + entry.getKey());
        }
    }


    public List<String> viewUserAccounts() {
        return new ArrayList<>(userAccounts);
    }


    public void createUserAccount(String email, String role) {
        userAccountsMap.put(email, role);
        refreshUserAccounts();
        accountCreationSuccess = true;
        System.out.printf("Created account with email: %s, role: %s%n", email, role);
    }


    public boolean isAccountCreationSuccessful() {
        return accountCreationSuccess;
    }


    public void deleteUserAccount(String email) {
        if (userAccountsMap.containsKey(email)) {
            userAccountsMap.remove(email);
            refreshUserAccounts();
            accountDeletionSuccess = true;
            System.out.printf("Deleted account with email: %s%n", email);
        } else {
            accountDeletionSuccess = false;
            System.out.printf("Failed to delete account with email: %s%n", email);
        }
    }


    public boolean isAccountDeletionSuccessful() {
        return accountDeletionSuccess;
    }


    public void updateUserRole(String email, String newRole) {
        if (userAccountsMap.containsKey(email)) {
            userAccountsMap.put(email, newRole);
            refreshUserAccounts();
            System.out.printf("Updated role for %s to %s%n", email, newRole);
        } else {
            System.out.printf("User with email %s not found!%n", email);
        }
    }


    public void approveInstructorRegistration(String email) {
        if (userAccountsMap.containsKey(email) && userAccountsMap.get(email).equals("Instructor")) {
            System.out.printf("Instructor with email %s approved successfully.%n", email);
        } else {
            System.out.printf("No instructor found with email %s or the user is not an instructor.%n", email);
        }
    }


    public boolean userExists(String email) {
        return userAccountsMap.containsKey(email);
    }

    public static void main(String[] args) {
        UserManagement userManager = new UserManagement();

        System.out.println("User Accounts:");
        for (String account : userManager.viewUserAccounts()) {
            System.out.println(account);
        }


        userManager.createUserAccount("john.doe@gmail.com", "Client");


        userManager.deleteUserAccount("ameed@gmail.com");


        userManager.updateUserRole("amrojamhour4@gmail.com", "Admin");


        userManager.approveInstructorRegistration("zahi.q83@gmail.com");


        System.out.println("\nUpdated User Accounts:");
        for (String account : userManager.viewUserAccounts()) {
            System.out.println(account);
        }
    }
}
