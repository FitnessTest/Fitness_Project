package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AccountManagement class serves as a singleton manager for client profiles
 * in the fitness management system. It provides functionalities to create, update,
 * view, delete, and list client profiles.
 *
 * The class uses an in-memory list to store client profiles and employs logging
 * to track operations performed on these profiles.
 */
public class AccountManagement {
    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());
    private static AccountManagement instance;
    private static final List<ClientProfile> clientProfiles = new ArrayList<>();

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private AccountManagement() {}

    /**
     * Returns the singleton instance of AccountManagement.
     *
     * @return the singleton instance
     */
    public static AccountManagement getInstance() {
        if (instance == null) {
            instance = new AccountManagement();
        }
        return instance;
    }

    /**
     * Represents a client's profile with personal and fitness details.
     */
    public static class ClientProfile {
        private String name;
        private String email;
        private int age;
        private String fitnessGoals;
        private String dietaryPreferences;

        /**
         * Constructs a new ClientProfile.
         *
         * @param name                the client's name
         * @param email               the client's email
         * @param age                 the client's age
         * @param fitnessGoals        the client's fitness goals
         * @param dietaryPreferences  the client's dietary preferences
         */
        public ClientProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            this.name = name;
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
            this.email = email;
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            this.age = age;
            this.fitnessGoals = (fitnessGoals != null && !fitnessGoals.isEmpty()) ? fitnessGoals : "Not specified";
            this.dietaryPreferences = (dietaryPreferences != null && !dietaryPreferences.isEmpty()) ? dietaryPreferences : "Not specified";
        }

        /**
         * Retrieves the client's name.
         *
         * @return the client's name
         */
        public String getName() {
            return name;
        }

        /**
         * Updates the client's name.
         *
         * @param name the new name
         */
        public void setName(String name) {
            this.name = (name != null && !name.isEmpty()) ? name : "Unknown Name";
        }

        /**
         * Retrieves the client's email address.
         *
         * @return the client's email
         */
        public String getEmail() {
            return email;
        }

        /**
         * Updates the client's email address.
         *
         * @param email the new email address
         */
        public void setEmail(String email) {
            this.email = (email != null && email.contains("@")) ? email : "unknown@example.com";
        }

        /**
         * Retrieves the client's age.
         *
         * @return the client's age
         */
        public int getAge() {
            return age;
        }

        /**
         * Updates the client's age.
         *
         * @param age the new age
         */
        public void setAge(int age) {
            this.age = (age > 0) ? age : 18;
        }

        /**
         * Retrieves the client's fitness goals.
         *
         * @return the client's fitness goals
         */
        public String getFitnessGoals() {
            return fitnessGoals;
        }

        /**
         * Updates the client's fitness goals.
         *
         * @param fitnessGoals the new fitness goals
         */
        public void setFitnessGoals(String fitnessGoals) {
            this.fitnessGoals = (fitnessGoals != null && !fitnessGoals.isEmpty()) ? fitnessGoals : "General Fitness";
        }

        /**
         * Retrieves the client's dietary preferences.
         *
         * @return the client's dietary preferences
         */
        public String getDietaryPreferences() {
            return dietaryPreferences;
        }

        /**
         * Updates the client's dietary preferences.
         *
         * @param dietaryPreferences the new dietary preferences
         */
        public void setDietaryPreferences(String dietaryPreferences) {
            this.dietaryPreferences = (dietaryPreferences != null && !dietaryPreferences.isEmpty())
                    ? dietaryPreferences
                    : "No specific preferences";
        }

        @Override
        public String toString() {
            return "ClientProfile [name=" + name + ", email=" + email + ", age=" + age
                    + ", fitnessGoals=" + fitnessGoals + ", dietaryPreferences=" + dietaryPreferences + "]";
        }
    }

    /**
     * Creates a new client profile and adds it to the list.
     *
     * @param name                the client's name
     * @param email               the client's email
     * @param age                 the client's age
     * @param fitnessGoals        the client's fitness goals
     * @param dietaryPreferences  the client's dietary preferences
     */
    public static void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);
        logger.info(String.format("Created profile for: %s", name));
    }

    /**
     * Updates an existing client profile identified by the email.
     *
     * @param email                the email of the client to update
     * @param newName              the new name
     * @param newAge               the new age
     * @param newFitnessGoals      the new fitness goals
     * @param newDietaryPreferences the new dietary preferences
     */
    public static void updateProfile(String email, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                profile.setName(newName);
                profile.setAge(newAge);
                profile.setFitnessGoals(newFitnessGoals);
                profile.setDietaryPreferences(newDietaryPreferences);
                logger.info(String.format("Profile updated for: %s", email));
                return;
            }
        }
        logger.info(String.format("Profile not found for: %s", email));
    }

    /**
     * Retrieves a client profile by email.
     *
     * @param email the email of the client
     * @return the client profile, or null if not found
     */
    public static ClientProfile viewProfile(String email) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        logger.info(String.format("Profile not found for: %s", email));
        return null;
    }

    /**
     * Deletes a client profile by email.
     *
     * @param email the email of the client to delete
     */
    public static void deleteProfile(String email) {
        clientProfiles.removeIf(profile -> profile.getEmail().equals(email));
        logger.info(String.format("Deleted profile for: %s", email));
    }

    /**
     * Lists all client profiles in the system.
     */
    public static void listAllProfiles() {
        if (logger.isLoggable(Level.INFO)) {
            logProfileHeader();
            if (clientProfiles.isEmpty()) {
                logger.info("No profiles available.");
            } else {
                for (ClientProfile profile : clientProfiles) {
                    logProfileInfo(profile);
                }
            }
        }
    }

    private static void logProfileHeader() {
        logger.info(String.format("%-20s %-30s %-5s %-30s %-30s", "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences"));
        logger.info("-------------------------------------------------------------------------------------------------------");
    }

    private static void logProfileInfo(ClientProfile profile) {
        String profileInfo = String.format("%-20s %-30s %-5d %-30s %-30s",
                profile.getName(),
                profile.getEmail(),
                profile.getAge(),
                profile.getFitnessGoals(),
                profile.getDietaryPreferences());
        logger.info(profileInfo);
    }

    /**
     * Retrieves the list of client profiles.
     *
     * @return the list of client profiles
     */
    public static List<ClientProfile> getClientProfiles() {
        return clientProfiles;
    }
}
