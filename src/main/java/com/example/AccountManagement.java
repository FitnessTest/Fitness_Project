package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountManagement {

    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());

    // Singleton pattern - instance of AccountManagement
    private static AccountManagement instance;

    // List of client profiles
    private static List<ClientProfile> clientProfiles = new ArrayList<>();

    // Private constructor to prevent instantiation
    private AccountManagement() {}

    // Public method to get the single instance of AccountManagement
    public static AccountManagement getInstance() {
        if (instance == null) {
            instance = new AccountManagement();
        }
        return instance;
    }

    // ClientProfile class to represent a client's profile
    public static class ClientProfile {
        private String name;
        private String email;
        private int age;
        private String fitnessGoals;
        private String dietaryPreferences;

        public ClientProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.fitnessGoals = fitnessGoals;
            this.dietaryPreferences = dietaryPreferences;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFitnessGoals() {
            return fitnessGoals;
        }

        public void setFitnessGoals(String fitnessGoals) {
            this.fitnessGoals = fitnessGoals;
        }

        public String getDietaryPreferences() {
            return dietaryPreferences;
        }

        public void setDietaryPreferences(String dietaryPreferences) {
            this.dietaryPreferences = dietaryPreferences;
        }

        @Override
        public String toString() {
            return "ClientProfile [name=" + name + ", email=" + email + ", age=" + age
                    + ", fitnessGoals=" + fitnessGoals + ", dietaryPreferences=" + dietaryPreferences + "]";
        }
    }

    // Create a new client profile
    public static void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);

        // Use String.format to construct the message
        String message = String.format("Created profile for: %s", name);
        logger.info(message);  // Log the message
    }

    // Update an existing client profile
    public static void updateProfile(String email, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                profile.setName(newName);
                profile.setAge(newAge);
                profile.setFitnessGoals(newFitnessGoals);
                profile.setDietaryPreferences(newDietaryPreferences);

                // Use String.format to construct the message
                String message = String.format("Profile updated for: %s", email);
                logger.info(message);  // Log the message
                return;
            }
        }
        // Log message if profile is not found
        String message = String.format("Profile not found for: %s", email);
        logger.info(message);
    }

    // View a client profile by email
    public static ClientProfile viewProfile(String email) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        String message = String.format("Profile not found for: %s", email);
        logger.info(message);  // Log the message if profile is not found
        return null;
    }

    // Delete a client profile by email
    public static void deleteProfile(String email) {
        clientProfiles.removeIf(profile -> profile.getEmail().equals(email));

        // Use String.format to construct the message
        String message = String.format("Deleted profile for: %s", email);
        logger.info(message);  // Log the message
    }

    // List all client profiles
    public static void listAllProfiles() {
        if (clientProfiles.isEmpty()) {
            logger.info("No profiles available.");
        } else {
            // Only log the table header if there are profiles to display
            logger.info(String.format("%-20s %-30s %-5s %-30s %-30s", "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences"));
            logger.info("-------------------------------------------------------------------------------------------------------");

            // Log each profile conditionally
            for (ClientProfile profile : clientProfiles) {
                if (profile != null && profile.getName() != null && !profile.getName().isEmpty()) {
                    String profileInfo = String.format("%-20s %-30s %-5d %-30s %-30s",
                            profile.getName(),
                            profile.getEmail(),
                            profile.getAge(),
                            profile.getFitnessGoals(),
                            profile.getDietaryPreferences());

                    logger.info(profileInfo);
                } else {
                    logger.warning("Skipping profile with missing or empty required fields.");
                }
            }
        }
    }
}
