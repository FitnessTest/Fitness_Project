package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManagement {
    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());
    private static List<ClientProfile> clientProfiles = new ArrayList<>();

    // Constant for error messages
    private static final String EMAIL_NULL_ERROR = "Email cannot be null";

    // Private constructor to prevent instantiation
    private AccountManagement() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Set the client profiles list (synchronized for thread safety)
    public static synchronized void setClientProfiles(List<ClientProfile> clientProfiles) {
        if (clientProfiles == null) {
            throw new IllegalArgumentException("Client profiles list cannot be null");
        }
        AccountManagement.clientProfiles = clientProfiles;
    }

    // Create a new client profile
    public static void createProfile(String s, String name, int age, String fitnessGoals, String dietaryPreferences) {
        if (name == null || fitnessGoals == null || dietaryPreferences == null) {
            throw new IllegalArgumentException("Client profile fields cannot be null");
        }
        ClientProfile newProfile = new ClientProfile(name, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);
        logger.log(Level.INFO, "Created profile for: {0}", name);
    }

    // Update an existing client profile
    public static synchronized void updateProfile(String name, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        for (ClientProfile profile : clientProfiles) {
            if (profile.getName().equals(name)) {
                profile.setName(name);
                profile.setAge(newAge);
                profile.setFitnessGoals(newFitnessGoals);
                profile.setDietaryPreferences(newDietaryPreferences);
                logger.log(Level.INFO, "Profile updated for: {0}", name);
                return;
            }
        }
        logger.log(Level.WARNING, "Profile not found for: {0}", name);
    }

    // View a client profile by name
    public static ClientProfile viewProfile(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        for (ClientProfile profile : clientProfiles) {
            if (profile.getName().equals(name)) {
                return profile;
            }
        }
        logger.log(Level.WARNING, "Profile not found for: {0}", name);
        return null;
    }

    // Delete a client profile by name
    public static synchronized void deleteProfile(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        boolean isRemoved = clientProfiles.removeIf(profile -> profile.getName().equals(name));
        if (isRemoved) {
            logger.log(Level.INFO, "Deleted profile for: {0}", name);
        } else {
            logger.log(Level.WARNING, "Profile not found for deletion: {0}", name);
        }
    }

    // List all client profiles
    public static void listAllProfiles() {
        if (logger.isLoggable(Level.INFO)) {
            if (clientProfiles.isEmpty()) {
                logger.log(Level.INFO, "No profiles available.");
            } else {
                StringBuilder logMessage = new StringBuilder();
                logMessage.append(String.format("%-20s %-5s %-30s %-30s%n",
                        "Name", "Age", "Fitness Goals", "Dietary Preferences"));
                logMessage.append("-------------------------------------------------------------------------------------------------------\n");
                for (ClientProfile profile : clientProfiles) {
                    logMessage.append(String.format("%-20s %-5d %-30s %-30s%n",
                            profile.getName(),
                            profile.getAge(),
                            profile.getFitnessGoals(),
                            profile.getDietaryPreferences()));
                }
                logger.log(Level.INFO, logMessage.toString());
            }
        }
    }

    // ClientProfile inner class definition
    public static class ClientProfile {
        private String name;
        private int age;
        private String fitnessGoals;
        private String dietaryPreferences;

        public ClientProfile(String name, int age, String fitnessGoals, String dietaryPreferences) {
            if (name == null || fitnessGoals == null || dietaryPreferences == null) {
                throw new IllegalArgumentException("Client profile fields cannot be null");
            }
            this.name = name;
            this.age = age;
            this.fitnessGoals = fitnessGoals;
            this.dietaryPreferences = dietaryPreferences;
            logger.log(Level.INFO, "Created ClientProfile: {0}", this);
        }

        // Getters and Setters for remaining fields
        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            this.age = age;
        }

        public String getFitnessGoals() {
            return fitnessGoals;
        }

        public void setFitnessGoals(String fitnessGoals) {
            if (fitnessGoals == null) {
                throw new IllegalArgumentException("Fitness Goals cannot be null");
            }
            this.fitnessGoals = fitnessGoals;
        }

        public String getDietaryPreferences() {
            return dietaryPreferences;
        }

        public void setDietaryPreferences(String dietaryPreferences) {
            if (dietaryPreferences == null) {
                throw new IllegalArgumentException("Dietary Preferences cannot be null");
            }
            this.dietaryPreferences = dietaryPreferences;
        }

        @Override
        public String toString() {
            return "ClientProfile [name=" + name + ", age=" + age
                    + ", fitnessGoals=" + fitnessGoals + ", dietaryPreferences=" + dietaryPreferences + "]";
        }
    }
}