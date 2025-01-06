package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramExplorationAndEnrollment {

    private static final Logger logger = Logger.getLogger(ProgramExplorationAndEnrollment.class.getName());

    public static class Program {
        private String title;
        private String difficultyLevel;
        private String focusArea;
        private String schedule;
        private double price;

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
            return "Program [Title=" + title + ", Difficulty Level=" + difficultyLevel
                    + ", Focus Area=" + focusArea + ", Schedule=" + schedule + ", Price=" + price + "]";
        }
    }

    public static List<Program> programs;

    public static List<Program> getPrograms() {
        return programs;
    }

    public ProgramExplorationAndEnrollment() {
        programs = new ArrayList<>();
        programs.add(new Program("Weight Loss Bootcamp", "Beginner", "Weight Loss", "Mon-Wed 10:00 AM", 49.99));
        programs.add(new Program("Muscle Building Challenge", "Intermediate", "Muscle Building", "Tue-Thu 11:00 AM", 69.99));
        programs.add(new Program("Advanced Flexibility Training", "Advanced", "Flexibility", "Mon-Fri 8:00 AM", 89.99));
    }

    public static void listPrograms(List<Program> programs) {
        if (programs.isEmpty()) {
            // Clear the internal list if the passed list is empty
            ProgramExplorationAndEnrollment.programs.clear();  // Or set it to a new empty list
            logger.log(Level.INFO, "No programs available matching the criteria.");
        } else {
            ProgramExplorationAndEnrollment.programs = new ArrayList<>(programs);  // Update with the new list
            logger.log(Level.INFO, "Programs Available:");
            logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-20s %-10s%n", "Title", "Difficulty Level", "Focus Area", "Schedule", "Price"));
            logger.log(Level.INFO, "----------------------------------------------------------------------------------------------------");
            for (Program program : programs) {
                logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-20s %-10.2f%n", program.getTitle(), program.getDifficultyLevel(),
                        program.getFocusArea(), program.getSchedule(), program.getPrice()));
            }
        }
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
                logger.log(Level.INFO, "Successfully enrolled in: " + program.getTitle());
                return;
            }
        }
        logger.log(Level.WARNING, "Program not found: " + programTitle);
    }

    public static void viewSchedule(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                logger.log(Level.INFO, "Schedule for " + program.getTitle() + ": " + program.getSchedule());
                return;
            }
        }
        logger.log(Level.WARNING, "Program not found: " + programTitle);
    }
}
