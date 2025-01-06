package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountManagement {


    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());


    private static AccountManagement instance;


    private static final List<ClientProfile> clientProfiles = new ArrayList<>();


    private AccountManagement() {}


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
            // Basic validation for name
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            this.name = name;

            // Basic validation for email
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
            this.email = email;

            // Basic validation for age
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            this.age = age;

            // Optional validation for fitness goals
            this.fitnessGoals = (fitnessGoals != null && !fitnessGoals.isEmpty()) ? fitnessGoals : "Not specified";

            // Optional validation for dietary preferences
            this.dietaryPreferences = (dietaryPreferences != null && !dietaryPreferences.isEmpty()) ? dietaryPreferences : "Not specified";
        }

        // Getter and Setter for name
        // Getter and Setter for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = (name != null && !name.isEmpty()) ? name : "Unknown Name"; // Validating name
        }

        // Getter and Setter for email
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = (email != null && email.contains("@")) ? email : "unknown@example.com"; // Validating email
        }

        // Getter and Setter for age
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = (age > 0) ? age : 18; // Validating age
        }

        // Getter and Setter for fitnessGoals
        public String getFitnessGoals() {
            return fitnessGoals;
        }

        public void setFitnessGoals(String fitnessGoals) {
            this.fitnessGoals = (fitnessGoals != null && !fitnessGoals.isEmpty()) ? fitnessGoals : "General Fitness"; // Default if empty
        }

        // Getter and Setter for dietaryPreferences
        public String getDietaryPreferences() {
            return dietaryPreferences;
        }

        public void setDietaryPreferences(String dietaryPreferences) {
            this.dietaryPreferences = (dietaryPreferences != null && !dietaryPreferences.isEmpty())
                    ? dietaryPreferences
                    : "No specific preferences"; // Default if empty
        }

        @Override
        public String toString() {
            return "ClientProfile [name=" + name + ", email=" + email + ", age=" + age
                    + ", fitnessGoals=" + fitnessGoals + ", dietaryPreferences=" + dietaryPreferences + "]";
        }
    }


    public static void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);


        String message = String.format("Created profile for: %s", name);
        logger.info(message);
    }

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

        String message = String.format("Profile not found for: %s", email);
        logger.info(message);
    }


    public static ClientProfile viewProfile(String email) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        String message = String.format("Profile not found for: %s", email);
        logger.info(message);
        return null;
    }


    public static void deleteProfile(String email) {
        clientProfiles.removeIf(profile -> profile.getEmail().equals(email));


        String message = String.format("Deleted profile for: %s", email);
        logger.info(message);
    }

    // List all client profiles
    public static void listAllProfiles() {
        if (clientProfiles.isEmpty()) {
            logger.info("No profiles available.");
        } else {

            logger.info(String.format("%-20s %-30s %-5s %-30s %-30s", "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences"));
            logger.info("-------------------------------------------------------------------------------------------------------");



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
