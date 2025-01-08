package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages the creation, update, deletion, and listing of fitness programs.
 * It allows instructors to perform CRUD (Create, Read, Update, Delete) operations
 * on fitness programs, including setting program details like title, duration,
 * difficulty level, goals, price, schedule, and other related data.
 */
public class ProgramManagement {

    // Logger to log the operations
    private static final Logger logger = Logger.getLogger(ProgramManagement.class.getName());

    /**
     * Program class encapsulating details of a program.
     */
    public static class Program {
        private String title;
        private String duration;
        private String difficultyLevel;
        private String goals;
        private String videoTutorial;
        private String image;
        private String document;
        private double price;
        private String schedule;

        /**
         * Constructor for Program class.
         * @param title Program's title
         * @param duration Program's duration
         * @param difficultyLevel Program's difficulty level
         * @param goals Program's goals
         * @param videoTutorial Video tutorial for the program
         * @param image Image for the program
         * @param document Additional document for the program
         * @param price Program's price
         * @param schedule Program's schedule
         */
        public Program(String title, String duration, String difficultyLevel, String goals,
                       String videoTutorial, String image, String document,
                       double price, String schedule) {
            this.title = title;
            this.duration = duration;
            this.difficultyLevel = difficultyLevel;
            this.goals = goals;
            this.videoTutorial = videoTutorial;
            this.image = image;
            this.document = document;
            this.price = price;
            this.schedule = schedule;
        }

        /**
         * Getter for the title.
         * @return Program's title.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Override toString method for better display.
         * @return String representation of the program details.
         */
        @Override
        public String toString() {
            return "Program [title=" + title + ", duration=" + duration + ", difficultyLevel=" + difficultyLevel
                    + ", goals=" + goals + ", videoTutorial=" + videoTutorial + ", image=" + image + ", document="
                    + document + ", price=" + price + ", schedule=" + schedule + "]";
        }
    }

    // List to hold the created programs
    private static List<Program> programs;

    /**
     * Constructor to initialize the list of programs.
     */
    public ProgramManagement() {
        programs = new ArrayList<>();
    }

    /**
     * Getter for the list of programs.
     * @return List of all programs.
     */
    public static List<Program> getPrograms() {
        return programs;
    }

    /**
     * Method to create a new program.
     * @param title Program's title
     * @param duration Program's duration
     * @param difficultyLevel Program's difficulty level
     * @param goals Program's goals
     * @param videoTutorial Video tutorial for the program
     * @param image Image for the program
     * @param document Additional document for the program
     * @param price Program's price
     * @param schedule Program's schedule
     */
    public static void createProgram(String title, String duration, String difficultyLevel, String goals,
                                     String videoTutorial, String image, String document,
                                     double price, String schedule) {
        Program newProgram = new Program(title, duration, difficultyLevel, goals,
                videoTutorial, image, document, price, schedule);
        programs.add(newProgram);
        logger.log(Level.INFO, "Program \"{0}\" created successfully.", title);
    }

    /**
     * Method to update an existing program.
     * @param oldTitle The current title of the program to be updated
     * @param newTitle The new title for the program
     * @param newDuration The new duration for the program
     * @param newDifficultyLevel The new difficulty level for the program
     * @param newGoals The new goals for the program
     * @param newVideoTutorial The new video tutorial for the program
     * @param newImage The new image for the program
     * @param newDocument The new document for the program
     * @param newPrice The new price for the program
     * @param newSchedule The new schedule for the program
     * @return true if program is successfully updated, false otherwise
     */
    public static boolean updateProgram(String oldTitle, String newTitle, String newDuration, String newDifficultyLevel,
                                        String newGoals, String newVideoTutorial, String newImage, String newDocument,
                                        double newPrice, String newSchedule) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(oldTitle)) {
                program.title = newTitle;
                program.duration = newDuration;
                program.difficultyLevel = newDifficultyLevel;
                program.goals = newGoals;
                program.videoTutorial = newVideoTutorial;
                program.image = newImage;
                program.document = newDocument;
                program.price = newPrice;
                program.schedule = newSchedule;
                logger.log(Level.INFO, "Program \"{0}\" updated successfully.", oldTitle);
                return true;
            }
        }
        logger.log(Level.WARNING, "Program \"{0}\" not found.", oldTitle);
        return false;
    }

    /**
     * Method to delete a program.
     * @param title The title of the program to delete
     * @return true if program is successfully deleted, false otherwise
     */
    public static boolean deleteProgram(String title) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(title)) {
                programs.remove(program);
                logger.log(Level.INFO, "Program \"{0}\" deleted successfully.", title);
                return true;
            }
        }
        logger.log(Level.WARNING, "Program \"{0}\" not found.", title);
        return false;
    }

    /**
     * Method to list all programs.
     */
    public static void listAllPrograms() {
        if (programs.isEmpty()) {
            logger.log(Level.INFO, "No programs available.");
        } else {
            logger.log(Level.INFO, "All Programs:");
            logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-40s %-15s %-20s %-15s %-20s %-25s%n",
                    "Title", "Duration", "Difficulty", "Goals", "Price", "Schedule", "Tutorial", "Image", "Document"));
            logger.log(Level.INFO, "----------------------------------------------------------------------------------------------------------");

            for (Program program : programs) {
                logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-40s %-15.2f %-20s %-15s %-20s %-25s%n",
                        program.getTitle(), program.duration, program.difficultyLevel, program.goals,
                        program.price, program.schedule, program.videoTutorial, program.image, program.document));
            }

            logger.log(Level.INFO, "\nTotal Programs: {0}", programs.size());
        }
    }

    /**
     * Method to check if a program exists by title.
     * @param title The title of the program to check
     * @return true if the program exists, false otherwise
     */
    public static boolean programExists(String title) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
}
