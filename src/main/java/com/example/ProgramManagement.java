package com.example;


import java.util.ArrayList;
import java.util.List;

public class ProgramManagement {


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


    public static void createProgram(String title, String duration, String difficultyLevel, String goals,
                                     String videoTutorial, String image, String document,
                                     double price, String schedule) {
        Program newProgram = new Program(title, duration, difficultyLevel, goals,
                videoTutorial, image, document, price, schedule);
        programs.add(newProgram);
        System.out.println("Program \"" + title + "\" created successfully.");
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
                System.out.println("Program \"" + oldTitle + "\" updated successfully.");
                return true;
            }
        }
        System.out.println("Program \"" + oldTitle + "\" not found.");
        return false;
    }

    public static boolean deleteProgram(String title) {
        for (Program program : programs) {
            if (program.title.equalsIgnoreCase(title)) {
                programs.remove(program);
                System.out.println("Program \"" + title + "\" deleted successfully.");
                return true;
            }
        }
        System.out.println("Program \"" + title + "\" not found.");
        return false;
    }


    public static void listAllPrograms() {
        if (programs.isEmpty()) {
            System.out.println("No programs available.");
        } else {
            System.out.println("All Programs:");

            System.out.printf("%-30s %-20s %-20s %-40s %-15s %-20s %-15s %-10s %-25s%n",
                    "Title", "Duration", "Difficulty", "Goals", "Price", "Schedule", "Tutorial", "Image", "Document");
            System.out.println("----------------------------------------------------------------------------------------------------------");


            for (Program program : programs) {
                System.out.printf("%-30s %-20s %-20s %-40s %-15.2f %-20s %-15s %-20s %-25s%n",
                        program.title, program.duration, program.difficultyLevel, program.goals,
                        program.price, program.schedule, program.videoTutorial, program.image, program.document);
            }


            System.out.println("\nTotal Programs: " + programs.size());
        }
    }


}