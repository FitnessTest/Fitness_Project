package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManagement {
    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());
    private static List<ClientProfile> clientProfiles = new ArrayList<>();

    // Set client profiles (for initializing or replacing the list)
    public static synchronized void setClientProfiles(List<ClientProfile> clientProfiles) {
        if (clientProfiles == null) {
            throw new IllegalArgumentException("Client profiles list cannot be null");
        }
        AccountManagement.clientProfiles = clientProfiles;
    }

    public static class ClientProfile {
        private String name;
        private String email;
        private int age;
        private String fitnessGoals;
        private String dietaryPreferences;

        public ClientProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
            if (name == null || email == null || fitnessGoals == null || dietaryPreferences == null) {
                throw new IllegalArgumentException("Client profile fields cannot be null");
            }
            this.name = name;
            this.email = email;
            this.age = age;
            this.fitnessGoals = fitnessGoals;
            this.dietaryPreferences = dietaryPreferences;
            logger.log(Level.INFO, "Created ClientProfile: {0}", this.toString());
        }

        // Getter methods
        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }

        public String getFitnessGoals() {
            return fitnessGoals;
        }

        public String getDietaryPreferences() {
            return dietaryPreferences;
        }

        // Setter methods
        public void setName(String name) {
            if (name == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
            this.name = name;
        }

        public void setEmail(String email) {
            if (email == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }
            this.email = email;
        }

        public void setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            this.age = age;
        }

        public void setFitnessGoals(String fitnessGoals) {
            if (fitnessGoals == null) {
                throw new IllegalArgumentException("Fitness Goals cannot be null");
            }
            this.fitnessGoals = fitnessGoals;
        }

        public void setDietaryPreferences(String dietaryPreferences) {
            if (dietaryPreferences == null) {
                throw new IllegalArgumentException("Dietary Preferences cannot be null");
            }
            this.dietaryPreferences = dietaryPreferences;
        }

        @Override
        public String toString() {
            return "ClientProfile [name=" + name + ", email=" + email + ", age=" + age
                    + ", fitnessGoals=" + fitnessGoals + ", dietaryPreferences=" + dietaryPreferences + "]";
        }


    }

    private AccountManagement() {
        // Private constructor to prevent instantiation
    }

    public static synchronized void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);
        logger.log(Level.INFO, "Created profile for: {0}", name);
    }

    public static synchronized void updateProfile(String email, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                profile.setName(newName);
                profile.setAge(newAge);
                profile.setFitnessGoals(newFitnessGoals);
                profile.setDietaryPreferences(newDietaryPreferences);
                logger.log(Level.INFO, "Profile updated for: {0}", email);
                return;
            }
        }
        logger.log(Level.WARNING, "Profile not found for: {0}", email);
    }

    public static synchronized ClientProfile viewProfile(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        logger.log(Level.WARNING, "Profile not found for: {0}", email);
        return null;
    }

    public static synchronized void deleteProfile(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        boolean isRemoved = clientProfiles.removeIf(profile -> profile.getEmail().equals(email));
        if (isRemoved) {
            logger.log(Level.INFO, "Deleted profile for: {0}", email);
        } else {
            logger.log(Level.WARNING, "Profile not found for deletion: {0}", email);
        }
    }

    public static synchronized void listAllProfiles() {
        if (clientProfiles.isEmpty()) {
            logger.log(Level.INFO, "No profiles available.");
        } else {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append(String.format("%-20s %-30s %-5s %-30s %-30s%n",
                    "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences"));
            logMessage.append("-------------------------------------------------------------------------------------------------------\n");
            for (ClientProfile profile : clientProfiles) {
                logMessage.append(String.format("%-20s %-30s %-5d %-30s %-30s%n",
                        profile.getName(),
                        profile.getEmail(),
                        profile.getAge(),
                        profile.getFitnessGoals(),
                        profile.getDietaryPreferences()));
            }
            logger.log(Level.INFO, logMessage.toString());
        }
    }
}

