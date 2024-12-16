package com.example;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {


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


    private List<ClientProfile> clientProfiles;


    public AccountManagement() {
        clientProfiles = new ArrayList<>();
    }


    public void createProfile(String name, String email, int age, String fitnessGoals, String dietaryPreferences) {
        ClientProfile newProfile = new ClientProfile(name, email, age, fitnessGoals, dietaryPreferences);
        clientProfiles.add(newProfile);
        System.out.println("Created profile for: " + name);
    }


    public void updateProfile(String email, String newName, int newAge, String newFitnessGoals, String newDietaryPreferences) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                profile.setName(newName);
                profile.setAge(newAge);
                profile.setFitnessGoals(newFitnessGoals);
                profile.setDietaryPreferences(newDietaryPreferences);
                System.out.println("Profile updated for: " + email);
                return;
            }
        }
        System.out.println("Profile not found for: " + email);
    }

    // View the profile of a client by email
    public ClientProfile viewProfile(String email) {
        for (ClientProfile profile : clientProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        System.out.println("Profile not found for: " + email);
        return null;
    }


    public void deleteProfile(String email) {
        clientProfiles.removeIf(profile -> profile.getEmail().equals(email));
        System.out.println("Deleted profile for: " + email);
    }


    public void listAllProfiles() {
        if (clientProfiles.isEmpty()) {
            System.out.println("No profiles available.");
        } else {
            // Print the table header
            System.out.printf("%-20s %-30s %-5s %-30s %-30s%n", "Name", "Email", "Age", "Fitness Goals", "Dietary Preferences");
            System.out.println("-------------------------------------------------------------------------------------------------------");

            // Print each profile in a row
            for (ClientProfile profile : clientProfiles) {
                System.out.printf("%-20s %-30s %-5d %-30s %-30s%n",
                        profile.getName(),
                        profile.getEmail(),
                        profile.getAge(),
                        profile.getFitnessGoals(),
                        profile.getDietaryPreferences());
            }
        }
    }
}
