package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountManagement {


    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());


    private static AccountManagement instance;


    private static List<ClientProfile> clientProfiles = new ArrayList<>();


    private AccountManagement() {}


    public static AccountManagement getInstance() {
        if (instance == null) {
            instance = new AccountManagement();
        }
        return instance;
    }


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


                String message = String.format("Profile updated for: %s", email);
                logger.info(message);
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
        logger.info(message);  // Log the message
    }


    public static void listAllProfiles() {
        if (clientProfiles.isEmpty()) {
            logger.info("No profiles available.");
        } else {
            // Log the table header
            logger.info(String.format("%-20s %-30s %-5s %-30s %-30s", "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences"));
            logger.info("-------------------------------------------------------------------------------------------------------");


            for (ClientProfile profile : clientProfiles) {

                if (profile.getName() != null && !profile.getName().isEmpty() &&
                        profile.getEmail() != null && !profile.getEmail().isEmpty() &&
                        profile.getFitnessGoals() != null && !profile.getFitnessGoals().isEmpty() &&
                        profile.getDietaryPreferences() != null && !profile.getDietaryPreferences().isEmpty()) {

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
