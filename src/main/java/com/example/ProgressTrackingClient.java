package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The ProgressTrackingClient class is used to manage and track the fitness progress of clients.
 * It provides functionality for updating progress, adding and removing achievements, and viewing client data.
 */
public class ProgressTrackingClient {

    private static final Logger logger = Logger.getLogger(ProgressTrackingClient.class.getName());

    /**
     * Retrieves the map containing the fitness progress data of all clients.
     *
     * @return A map with client IDs as keys and their fitness progress as values.
     */
    public static Map<String, FitnessProgress> getClientProgressMap() {
        return clientProgressMap;
    }

    /**
     * A nested class that represents the fitness progress of a client.
     */
    public static class FitnessProgress {
        private double weight;
        private double height; // Add height for calculating BMI
        private double bmi;
        private int attendanceCount;
        private Map<String, String> achievements;

        /**
         * Constructor to initialize the fitness progress of a client.
         *
         * @param weight          The weight of the client in kilograms.
         * @param height          The height of the client in meters.
         * @param attendanceCount The number of fitness sessions attended by the client.
         * @throws IllegalArgumentException if the height is less than or equal to zero.
         */
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

        /**
         * Calculates the Body Mass Index (BMI) of the client.
         *
         * @param weight The weight of the client in kilograms.
         * @param height The height of the client in meters.
         * @return The calculated BMI value.
         */
        private double calculateBMI(double weight, double height) {
            return weight / (height * height);
        }

        /**
         * Adds an achievement to the client's progress.
         *
         * @param achievement The name of the achievement.
         * @param date        The date of the achievement.
         */
        public void addAchievement(String achievement, String date) {
            achievements.put(achievement, date);
        }
        /**
         * Returns the weight of the client.
         *
         * @return The weight of the client.
         */
        public double getWeight() {
            return weight;
        }

        /**
         * Returns the height of the client.
         *
         * @return The height of the client.
         */
        public double getHeight() {
            return height;
        }
        /**
         * Returns the BMI of the client.
         *
         * @return The BMI of the client.
         */
        public double getBmi() {
            return bmi;
        }

        /**
         * Returns the attendance count of the client.
         *
         * @return The attendance count of the client.
         */
        public int getAttendanceCount() {
            return attendanceCount;
        }
        /**
         * Returns the achievements of the client.
         *
         * @return A map of achievements with their respective dates.
         */
        public Map<String, String> getAchievements() {
            return achievements;
        }

        @Override
        public String toString() {
            return "FitnessProgress [Weight=" + weight + ", Height=" + height + ", BMI=" + bmi + ", Attendance Count="
                    + attendanceCount + ", Achievements=" + achievements + "]";
        }
    }

    private static Map<String, FitnessProgress> clientProgressMap = new HashMap<>();

    /**
     * Private constructor to prevent instantiation of the ProgressTrackingClient class.
     */
    private ProgressTrackingClient() {
        throw new UnsupportedOperationException("Instance creation is not supported for ProgressTrackingClient.");
    }

    /**
     * Updates the fitness progress of a client.
     *
     * @param clientId        The ID of the client.
     * @param weight          The updated weight of the client in kilograms.
     * @param height          The updated height of the client in meters.
     * @param attendanceCount The updated attendance count of the client.
     * @throws IllegalArgumentException if the height is less than or equal to zero.
     */
    public static void updateClientProgress(String clientId, double weight, double height, int attendanceCount) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        FitnessProgress progress = new FitnessProgress(weight, height, attendanceCount);
        clientProgressMap.put(clientId, progress);
        logger.info("Updated progress for client ID: " + clientId);
    }

    /**
     * Displays the fitness progress of a client.
     *
     * @param clientId The ID of the client.
     */
    public static void showClientProgress(String clientId) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            logger.info("Progress for client ID " + clientId + ": " + progress);
        } else {
            logger.warning("No progress data found for client ID: " + clientId);
        }
    }

    /**
     * Adds an achievement to the client's progress.
     *
     * @param clientId    The ID of the client.
     * @param achievement The name of the achievement.
     * @param date        The date of the achievement.
     */
    public static void addAchievement(String clientId, String achievement, String date) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null) {
            progress.addAchievement(achievement, date);
            logger.info("Achievement added for client ID " + clientId + ": " + achievement);
        } else {
            logger.warning("No progress data found for client ID: " + clientId);
        }
    }

    /**
     * Removes an achievement from the client's progress.
     *
     * @param clientId    The ID of the client.
     * @param achievement The name of the achievement to be removed.
     */
    public static void removeAchievement(String clientId, String achievement) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null && progress.getAchievements().containsKey(achievement)) {
            progress.getAchievements().remove(achievement);
            logger.info("Achievement removed for client ID " + clientId + ": " + achievement);
        } else {
            logger.warning("Achievement not found for client ID: " + clientId);
        }
    }

    /**
     * Lists all achievements of a client.
     *
     * @param clientId The ID of the client.
     */
    public static void listAchievements(String clientId) {
        FitnessProgress progress = clientProgressMap.get(clientId);
        if (progress != null && !progress.getAchievements().isEmpty()) {
            logger.info("Achievements for client ID " + clientId + ":");
            progress.getAchievements().forEach((achievement, date) -> {
                logger.info("Achievement: " + achievement + " on " + date);
            });
        } else {
            logger.warning("No achievements found for client ID: " + clientId);
        }
    }
}
