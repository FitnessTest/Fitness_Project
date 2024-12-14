package com.example;

import java.util.ArrayList;
import java.util.List;

public class ProgramExplorationAndEnrollment {


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


    private List<Program> programs;


    public ProgramExplorationAndEnrollment() {
        programs = new ArrayList<>();

        programs.add(new Program("Weight Loss Bootcamp", "Beginner", "Weight Loss", "Mon-Wed 10:00 AM", 49.99));
        programs.add(new Program("Muscle Building Challenge", "Intermediate", "Muscle Building", "Tue-Thu 11:00 AM", 69.99));
        programs.add(new Program("Advanced Flexibility Training", "Advanced", "Flexibility", "Mon-Fri 8:00 AM", 89.99));
    }


    public List<Program> browsePrograms(String difficultyLevel, String focusArea) {
        List<Program> filteredPrograms = new ArrayList<>();
        for (Program program : programs) {
            if ((difficultyLevel == null || program.getDifficultyLevel().equalsIgnoreCase(difficultyLevel)) &&
                    (focusArea == null || program.getFocusArea().equalsIgnoreCase(focusArea))) {
                filteredPrograms.add(program);
            }
        }
        return filteredPrograms;
    }


    public void enrollInProgram(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                System.out.println("Successfully enrolled in: " + program.getTitle());
                return;
            }
        }
        System.out.println("Program not found: " + programTitle);
    }


    public void viewSchedule(String programTitle) {
        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(programTitle)) {
                System.out.println("Schedule for " + program.getTitle() + ": " + program.getSchedule());
                return;
            }
        }
        System.out.println("Program not found: " + programTitle);
    }


    public static void main(String[] args) {
        ProgramExplorationAndEnrollment programManagement = new ProgramExplorationAndEnrollment();


        System.out.println("Browsing programs with 'Intermediate' difficulty and 'Muscle Building' focus:");
        List<Program> filteredPrograms = programManagement.browsePrograms("Intermediate", "Muscle Building");
        for (Program program : filteredPrograms) {
            System.out.println(program);
        }


        programManagement.enrollInProgram("Muscle Building Challenge");


        programManagement.viewSchedule("Weight Loss Bootcamp");


        programManagement.enrollInProgram("Yoga for All");
    }
}
