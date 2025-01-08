package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramExplorationAndEnrollment {

    private static final Logger logger = Logger.getLogger(ProgramExplorationAndEnrollment.class.getName());

    public static class Program {
        private final String title;
        private final String difficultyLevel;
        private final String focusArea;
        private final String schedule;
        private final double price;

        public Program(String title, String difficultyLevel, String focusArea, String schedule, double price) {
            this.title = title;
            this.difficultyLevel = difficultyLevel;
            this.focusArea = focusArea;
            this.schedule = schedule;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public String getFocusArea() {
            return focusArea;
        }

        public String getSchedule() {
            return schedule;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("Program [Title=%s, Difficulty Level=%s, Focus Area=%s, Schedule=%s, Price=%.2f]",
                    title, difficultyLevel, focusArea, schedule, price);
        }
    }

    public static List<Program> programs = new ArrayList<>();
    private static final List<String> enrolledPrograms = new ArrayList<>();

    public ProgramExplorationAndEnrollment() {
        if (programs.isEmpty()) { // Initialize only once
            programs.add(new Program("Weight Loss Bootcamp", "Beginner", "Weight Loss", "Mon-Wed 10:00 AM", 49.99));
            programs.add(new Program("Muscle Building Challenge", "Intermediate", "Muscle Building", "Tue-Thu 11:00 AM", 69.99));
            programs.add(new Program("Advanced Flexibility Training", "Advanced", "Flexibility", "Mon-Fri 8:00 AM", 89.99));
        }
    }

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

    public static boolean isEnrolled(String programTitle) {
        return enrolledPrograms.contains(programTitle);
    }

    public static String viewSchedule(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                return program.getSchedule();
            }
        }
        throw new IllegalArgumentException("Program not found: " + programTitle);
    }
    public static List<Program> getPrograms() {
        return programs;
    }
}
