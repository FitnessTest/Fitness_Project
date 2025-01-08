package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class allows users to explore and enroll in fitness programs.
 * It provides functionality to browse available programs, view program details,
 * and enroll in a program.
 */
public class ProgramExplorationAndEnrollment {

    private static final Logger logger = Logger.getLogger(ProgramExplorationAndEnrollment.class.getName());

    /**
     * Represents a fitness program with details such as title, difficulty level,
     * focus area, schedule, and price.
     */
    public static class Program {
        private final String title;
        private final String difficultyLevel;
        private final String focusArea;
        private final String schedule;
        private final double price;

        /**
         * Constructor to create a new Program instance.
         *
         * @param title            The title of the program.
         * @param difficultyLevel The difficulty level of the program.
         * @param focusArea       The focus area of the program.
         * @param schedule        The schedule of the program.
         * @param price           The price of the program.
         */
        public Program(String title, String difficultyLevel, String focusArea, String schedule, double price) {
            this.title = title;
            this.difficultyLevel = difficultyLevel;
            this.focusArea = focusArea;
            this.schedule = schedule;
            this.price = price;
        }

        /**
         * Gets the title of the program.
         *
         * @return The title of the program.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Gets the difficulty level of the program.
         *
         * @return The difficulty level of the program.
         */
        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        /**
         * Gets the focus area of the program.
         *
         * @return The focus area of the program.
         */
        public String getFocusArea() {
            return focusArea;
        }

        /**
         * Gets the schedule of the program.
         *
         * @return The schedule of the program.
         */
        public String getSchedule() {
            return schedule;
        }

        /**
         * Gets the price of the program.
         *
         * @return The price of the program.
         */
        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("Program [Title=%s, Difficulty Level=%s, Focus Area=%s, Schedule=%s, Price=%.2f]",
                    title, difficultyLevel, focusArea, schedule, price);
        }
    }

    /**
     * A list that stores all available fitness programs.
     */
    public static List<Program> programs = new ArrayList<>();

    private static final List<String> enrolledPrograms = new ArrayList<>();

    /**
     * Constructor to initialize programs. This is called when the class is instantiated.
     * Initializes default programs if the list is empty.
     */
    public ProgramExplorationAndEnrollment() {
        if (programs.isEmpty()) { // Initialize only once
            programs.add(new Program("Weight Loss Bootcamp", "Beginner", "Weight Loss", "Mon-Wed 10:00 AM", 49.99));
            programs.add(new Program("Muscle Building Challenge", "Intermediate", "Muscle Building", "Tue-Thu 11:00 AM", 69.99));
            programs.add(new Program("Advanced Flexibility Training", "Advanced", "Flexibility", "Mon-Fri 8:00 AM", 89.99));
        }
    }

    /**
     * Lists all programs in a formatted way.
     *
     * @param programList List of programs to display.
     * @return A list of programs.
     */
    public static List<Program> listPrograms(List<Program> programList) {
        if (programList.isEmpty()) {
            logger.log(Level.INFO, "No programs available matching the criteria.");
        } else {
            logger.log(Level.INFO, "Programs Available:");
            logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-20s %-10s",
                    "Title", "Difficulty Level", "Focus Area", "Schedule", "Price"));
            logger.log(Level.INFO, "----------------------------------------------------------------------------------------------------");
            for (Program program : programList) {
                logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-20s $%-10.2f",
                        program.getTitle(), program.getDifficultyLevel(), program.getFocusArea(), program.getSchedule(), program.getPrice()));
            }
        }
        return programList;
    }

    /**
     * Filters programs based on difficulty level, focus area, and price range.
     *
     * @param difficultyLevel The desired difficulty level of the program.
     * @param focusArea       The desired focus area of the program.
     * @param minPrice        The minimum price for the program.
     * @param maxPrice        The maximum price for the program.
     * @return A list of filtered programs.
     */
    public static List<Program> browsePrograms(String difficultyLevel, String focusArea, double minPrice, double maxPrice) {
        List<Program> filteredPrograms = new ArrayList<>();
        for (Program program : programs) {
            boolean matchesDifficulty = (difficultyLevel == null || program.getDifficultyLevel().equalsIgnoreCase(difficultyLevel));
            boolean matchesFocusArea = (focusArea == null || program.getFocusArea().equalsIgnoreCase(focusArea));
            boolean matchesPrice = (program.getPrice() >= minPrice && program.getPrice() <= maxPrice);

            if (matchesDifficulty && matchesFocusArea && matchesPrice) {
                filteredPrograms.add(program);
            }
        }
        return filteredPrograms;
    }

    /**
     * Enrolls the user in a program if they are not already enrolled.
     *
     * @param programTitle The title of the program to enroll in.
     * @throws IllegalArgumentException If the program is already enrolled or not found.
     */
    public static void enrollInProgram(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                if (!enrolledPrograms.contains(programTitle)) {
                    enrolledPrograms.add(programTitle);
                    logger.log(Level.INFO, "Successfully enrolled in: " + program.getTitle());
                } else {
                    throw new IllegalArgumentException("Already enrolled in: " + program.getTitle());
                }
                return;
            }
        }
        throw new IllegalArgumentException("Program not found: " + programTitle);
    }

    /**
     * Checks if the user is already enrolled in a specific program.
     *
     * @param programTitle The title of the program to check enrollment status.
     * @return True if the user is enrolled, false otherwise.
     */
    public static boolean isEnrolled(String programTitle) {
        return enrolledPrograms.contains(programTitle);
    }

    /**
     * Views the schedule for a specific program.
     *
     * @param programTitle The title of the program to view its schedule.
     * @return The schedule of the program.
     * @throws IllegalArgumentException If the program is not found.
     */
    public static String viewSchedule(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                return program.getSchedule();
            }
        }
        throw new IllegalArgumentException("Program not found: " + programTitle);
    }

    /**
     * Returns the list of available programs.
     *
     * @return A list of programs.
     */
    public static List<Program> getPrograms() {
        return programs;
    }
}
