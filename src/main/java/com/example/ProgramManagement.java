package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramManagement {

    private static final Logger logger = Logger.getLogger(ProgramManagement.class.getName());

    public static class Program {
        public String title;
        private String duration;
        private String difficultyLevel;
        private String goals;
        private String videoTutorial;
        private String image;
        private String document;
        private double price;
        private String schedule;

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

        @Override
        public String toString() {
            return "Program [title=" + title + ", duration=" + duration + ", difficultyLevel=" + difficultyLevel
                    + ", goals=" + goals + ", videoTutorial=" + videoTutorial + ", image=" + image + ", document="
                    + document + ", price=" + price + ", schedule=" + schedule + "]";
        }
    }

    private static List<Program> programs;

    public ProgramManagement() {
        programs = new ArrayList<>();
    }

    public static List<Program> getPrograms() {
        return programs;  // Added method to get the list of programs
    }

    public static void createProgram(String title, String duration, String difficultyLevel, String goals,
                                     String videoTutorial, String image, String document,
                                     double price, String schedule) {
        Program newProgram = new Program(title, duration, difficultyLevel, goals,
                videoTutorial, image, document, price, schedule);
        programs.add(newProgram);
        logger.log(Level.INFO, "Program \"{0}\" created successfully.", title);
    }

    public static boolean updateProgram(String oldTitle, String newTitle, String newDuration, String newDifficultyLevel,
                                        String newGoals, String newVideoTutorial, String newImage, String newDocument,
                                        double newPrice, String newSchedule) {
        for (Program program : programs) {
            if (program.title.equalsIgnoreCase(oldTitle)) {
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

    public static boolean deleteProgram(String title) {
        for (Program program : programs) {
            if (program.title.equalsIgnoreCase(title)) {
                programs.remove(program);
                logger.log(Level.INFO, "Program \"{0}\" deleted successfully.", title);
                return true;
            }
        }
        logger.log(Level.WARNING, "Program \"{0}\" not found.", title);
        return false;
    }

    public static void listAllPrograms() {
        if (programs.isEmpty()) {
            logger.log(Level.INFO, "No programs available.");
        } else {
            logger.log(Level.INFO, "All Programs:");
            logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-40s %-15s %-20s %-15s %-10s %-25s%n",
                    "Title", "Duration", "Difficulty", "Goals", "Price", "Schedule", "Tutorial", "Image", "Document"));
            logger.log(Level.INFO, "----------------------------------------------------------------------------------------------------------");

            for (Program program : programs) {
                logger.log(Level.INFO, String.format("%-30s %-20s %-20s %-40s %-15.2f %-20s %-15s %-20s %-25s%n",
                        program.title, program.duration, program.difficultyLevel, program.goals,
                        program.price, program.schedule, program.videoTutorial, program.image, program.document));
            }

            logger.log(Level.INFO, "\nTotal Programs: {0}", programs.size());
        }
    }
}
