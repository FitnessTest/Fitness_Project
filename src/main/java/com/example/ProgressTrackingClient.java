package com.example;

import java.util.HashMap;
import java.util.Map;

public class ProgressTrackingClient {


    public static class FitnessProgress {
        private double weight;
        private double bmi;
        private int attendanceCount;
        private Map<String, String> achievements;


        public FitnessProgress(double weight, double height, int attendanceCount) {
            this.weight = weight;
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
            return "FitnessProgress [Weight=" + weight + ", BMI=" + bmi + ", Attendance Count=" + attendanceCount
                    + ", Achievements=" + achievements + "]";
        }
    }


    private Map<String, FitnessProgress> clientProgressMap;


    public ProgressTrackingClient() {
        clientProgressMap = new HashMap<>();
    }


    public void updateClientProgress(String clientId, double weight, double height, int attendanceCount) {
        FitnessProgress progress = new FitnessProgress(weight, height, attendanceCount);
        clientProgressMap.put(clientId, progress);
        System.out.println("Updated progress for client ID: " + clientId);
    }


    public void showClientProgress(String clientId) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            System.out.println("Progress for client ID " + clientId + ": " + progress);
        } else {
            System.out.println("No progress data found for client ID: " + clientId);
        }
    }


    public void addAchievement(String clientId, String achievement, String date) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            progress.addAchievement(achievement, date);
            System.out.println("Achievement added for client ID " + clientId + ": " + achievement);
        } else {
            System.out.println("No progress data found for client ID: " + clientId);
        }
    }


    public static void main(String[] args) {
        ProgressTrackingClient progressTracking = new ProgressTrackingClient();


        progressTracking.updateClientProgress("C001", 75.5, 1.75, 20);


        progressTracking.addAchievement("C001", "Completed 30-day Fitness Challenge", "2024-12-15");
        progressTracking.addAchievement("C001", "Lost 5kg", "2024-12-10");


        progressTracking.showClientProgress("C001");


        progressTracking.showClientProgress("C002");
    }
}
