package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManagement {

    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());

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

            // Log the creation of the ClientProfile object
            logger.log(Level.INFO, "Created ClientProfile: {0}", this.toString());
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

    private static List<ClientProfile> clientProfiles;

    private AccountManagement() {
        clientProfiles = new ArrayList<>();
    }

    public static void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);
        logger.log(Level.INFO, "Created profile for: {0}", name);
    }

    public static void updateProfile(String email, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
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

    // View the profile of a client by email
    public static ClientProfile viewProfile(String email) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        logger.log(Level.WARNING, "Profile not found for: {0}", email);
        return null;
    }

    public static void deleteProfile(String email) {
        boolean isRemoved = clientProfiles.removeIf(profile -> profile.getEmail().equals(email));
        if (isRemoved) {
            logger.log(Level.INFO, "Deleted profile for: {0}", email);
        } else {
            logger.log(Level.WARNING, "Profile not found for deletion: {0}", email);
        }
    }

    public static void listAllProfiles() {
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
