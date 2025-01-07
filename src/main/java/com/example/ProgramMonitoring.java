package com.example;

import java.util.*;
import java.util.logging.*;

public class ProgramMonitoring {

    private static final String WEIGHT_LOSS_PROGRAM = "Weight Loss";
    private static final String STRENGTH_TRAINING_PROGRAM = "Strength Training";
    private static final String CARDIO_FITNESS_PROGRAM = "Cardio Fitness";
    private static final String YOGA_AND_FLEXIBILITY_PROGRAM = "Yoga and Flexibility";
    private static final String HIIT_BOOTCAMP_PROGRAM = "HIIT Bootcamp";

    private static final Logger logger = Logger.getLogger(ProgramMonitoring.class.getName());
    public static Map<String, Program> programs = new HashMap<>();
    public static Map<String, Client> clients = new HashMap<>();


    public ProgramMonitoring() {

        programs.put(WEIGHT_LOSS_PROGRAM, new Program(WEIGHT_LOSS_PROGRAM, 3000, 80));
        programs.put(STRENGTH_TRAINING_PROGRAM, new Program(STRENGTH_TRAINING_PROGRAM, 4000, 60));
        programs.put(CARDIO_FITNESS_PROGRAM, new Program(CARDIO_FITNESS_PROGRAM, 2000, 100));
        programs.put(YOGA_AND_FLEXIBILITY_PROGRAM, new Program(YOGA_AND_FLEXIBILITY_PROGRAM, 2500, 70));
        programs.put(HIIT_BOOTCAMP_PROGRAM, new Program(HIIT_BOOTCAMP_PROGRAM, 3500, 50));


        clients.put("amrojamhour4@gmail.com", new Client("amrojamhour4@gmail.com", "Amr Jamhour", WEIGHT_LOSS_PROGRAM));
        clients.put("kebab830@gmail.com", new Client("kebab830@gmail.com", "Ihab Habash",  YOGA_AND_FLEXIBILITY_PROGRAM));
        clients.put("zahi.q83@gmail.com", new Client("zahi.q83@gmail.com", "Zahi Qudu", CARDIO_FITNESS_PROGRAM));
        clients.put("eternalnightmare@gmail.com", new Client("eternalnightmare@gmail.com", "Ameed Diab", STRENGTH_TRAINING_PROGRAM));
        clients.put("potetogamer@gmail.com", new Client("potetogamer@gmail.com", "Momen Habash", HIIT_BOOTCAMP_PROGRAM));
    }


    public static List<String> viewMostPopularPrograms() {
        List<Program> programList = new ArrayList<>(programs.values());
        programList.sort(Comparator.comparingInt(Program::getEnrollmentCount).reversed());

        List<String> result = new ArrayList<>();
        result.add(String.format("%-25s | %-15s", "Program Name", "Enrollment Count"));
        result.add("-".repeat(42));

        for (Program program : programList) {
            result.add(String.format("%-25s | %-15d", program.getName(), program.getEnrollmentCount()));
        }

        logger.info("Most Popular Programs: \n" + String.join("\n", result));
        return result;
    }


    public static void generateReports() {
        logger.info("Program Revenue Report:");
        programs.values().forEach(program -> logger.info(program.getName() + " - Revenue: $" + program.getRevenue()));

        logger.info("\nProgram Attendance Report:");
        programs.values().forEach(program -> logger.info(program.getName() + " - Attendance: " + program.getEnrollmentCount()));

        logger.info("\nClient Progress Report:");
        clients.values().forEach(client -> logger.info(client.getName() + " - Progress in " + client.getProgramName() + ": In Progress"));
    }


    public static int trackProgramsStatus() {
        logger.info("\nActive Programs:");
        programs.values().stream().filter(program -> program.getEnrollmentCount() > 0)
                .forEach(program -> logger.info(program.getName() + " - Active"));

        logger.info("\nCompleted Programs:");
        programs.values().stream().filter(program -> program.getEnrollmentCount() == 0)
                .forEach(program -> logger.info(program.getName() + " - Completed"));
        return 0;
    }


    public class Program {
        private final String name;
        private final double revenue;
        private final int enrollmentCount;

        public Program(String name, double revenue, int enrollmentCount) {
            this.name = name;
            this.revenue = revenue;
            this.enrollmentCount = enrollmentCount;
        }

        public String getName() {
            return name;
        }

        public double getRevenue() {
            return revenue;
        }

        public int getEnrollmentCount() {
            return enrollmentCount;
        }
    }


    public static class Client {
        private final String email;
        private final String name;
        private final String programName;

        public Client(String email, String name, String programName) {
            this.email = email;
            this.name = name;
            this.programName = programName;
        }

        public String getName() {
            return name;
        }

        public String getProgramName() {
            return programName;
        }
    }

}
