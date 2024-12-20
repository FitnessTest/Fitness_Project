package com.example;

import java.util.HashMap;
import java.util.Map;

public class ProgressTrackingClient {


    public static class FitnessProgress {
        private double weight;
        private double height; // Add height for calculating BMI
        private double bmi;
        private int attendanceCount;
        private Map<String, String> achievements;


        public FitnessProgress(double weight, double height, int attendanceCount) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than zero.");
            }
            this.weight = weight;
            this.height = height;
            this.bmi = calculateBMI(weight, height);
            this.attendanceCount = attendanceCount;
            this.achievements = new HashMap<>();
        }


        private double calculateBMI(double weight, double height) {
            return weight / (height * height);
        }


        public void addAchievement(String achievement, String date) {
            achievements.put(achievement, date);
        }


        public double getWeight() {
            return weight;
        }

        public double getHeight() {
            return height;
        }

        public double getBmi() {
            return bmi;
        }

        public int getAttendanceCount() {
            return attendanceCount;
        }

        public Map<String, String> getAchievements() {
            return achievements;
        }


        @Override
        public String toString() {
            return "FitnessProgress [Weight=" + weight + ", Height=" + height + ", BMI=" + bmi + ", Attendance Count="
                    + attendanceCount + ", Achievements=" + achievements + "]";
        }
    }


    private static Map<String, FitnessProgress> clientProgressMap;


    public ProgressTrackingClient() {
        clientProgressMap = new HashMap<>();
    }


    public static void updateClientProgress(String clientId, double weight, double height, int attendanceCount) {
        try {
            FitnessProgress progress = new FitnessProgress(weight, height, attendanceCount);
            clientProgressMap.put(clientId, progress);
            System.out.println("Updated progress for client ID: " + clientId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating progress: " + e.getMessage());
        }
    }


    public static void showClientProgress(String clientId) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            System.out.println("Progress for client ID " + clientId + ": " + progress);
        } else {
            System.out.println("No progress data found for client ID: " + clientId);
        }
    }

    public static void addAchievement(String clientId, String achievement, String date) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            progress.addAchievement(achievement, date);
            System.out.println("Achievement added for client ID " + clientId + ": " + achievement);
        } else {
            System.out.println("No progress data found for client ID: " + clientId);
        }
    }


    public static void removeAchievement(String clientId, String achievement) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null && progress.getAchievements().containsKey(achievement)) {
            progress.getAchievements().remove(achievement);
            System.out.println("Achievement removed for client ID " + clientId + ": " + achievement);
        } else {
            System.out.println("Achievement not found for client ID: " + clientId);
        }
    }

    public static void listAchievements(String clientId) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null && !progress.getAchievements().isEmpty()) {
            System.out.println("Achievements for client ID " + clientId + ":");
            progress.getAchievements().forEach((achievement, date) ->
                    System.out.println("Achievement: " + achievement + " on " + date));
        } else {
            System.out.println("No achievements found for client ID: " + clientId);
        }
    }
}
