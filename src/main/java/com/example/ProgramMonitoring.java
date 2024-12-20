package com.example;

import java.util.*;

public class ProgramMonitoring {

    private static Map<String, Program> programs;
    private static Map<String, Client> clients;

    public ProgramMonitoring() {
        programs = new HashMap<>();
        clients = new HashMap<>();

        // Adding sample fitness programs with enrollment data
        programs.put("Weight Loss", new Program("Weight Loss", 3000, 80));
        programs.put("Strength Training", new Program("Strength Training", 4000, 60));
        programs.put("Cardio Fitness", new Program("Cardio Fitness", 2000, 100));
        programs.put("Yoga and Flexibility", new Program("Yoga and Flexibility", 2500, 70));
        programs.put("HIIT Bootcamp", new Program("HIIT Bootcamp", 3500, 50));

        // Adding sample clients enrolled in these fitness programs
        clients.put("amrojamhour4@gmail.com", new Client("amrojamhour4@gmail.com", "Amr Jamhour", "Weight Loss"));
        clients.put("kebab830@gmail.com", new Client("kebab830@gmail.com", "Ihab Habash", "Yoga and Flexibility"));
        clients.put("zahi.q83@gmail.com", new Client("zahi.q83@gmail.com", "Zahi Qudu", "Cardio Fitness"));
        clients.put("eternalnightmare@gmail.com", new Client("eternalnightmare@gmail.com", "Ameed Diab", "Strength Training"));
        clients.put("potetogamer@gmail.com", new Client("potetogamer@gmail.com", "Momen Habash", "HIIT Bootcamp"));
    }

    public static List<String> viewMostPopularPrograms() {
        List<Program> programList = new ArrayList<>(programs.values());
        programList.sort(Comparator.comparingInt(Program::getEnrollmentCount).reversed());

        String header = String.format("%-25s | %-15s", "Program Name", "Enrollment Count");
        String separator = "-".repeat(header.length());

        List<String> result = new ArrayList<>();
        result.add(header);
        result.add(separator);

        for (Program program : programList) {
            String row = String.format("%-25s | %-15d", program.getName(), program.getEnrollmentCount());
            result.add(row);
        }

        return result;
    }

    public static void generateReports() {
        System.out.println("Program Revenue Report:");
        for (Program program : programs.values()) {
            System.out.println(program.getName() + " - Revenue: $" + program.getRevenue());
        }

        System.out.println("\nProgram Attendance Report:");
        for (Program program : programs.values()) {
            System.out.println(program.getName() + " - Attendance: " + program.getEnrollmentCount());
        }

        System.out.println("\nClient Progress Report:");
        for (Client client : clients.values()) {
            System.out.println(client.getName() + " - Progress in " + client.getProgramName() + ": In Progress");
        }
    }

    public static void trackProgramsStatus() {
        System.out.println("\nActive Programs:");
        for (Program program : programs.values()) {
            if (program.getEnrollmentCount() > 0) {
                System.out.println(program.getName() + " - Active");
            }
        }

        System.out.println("\nCompleted Programs:");
        for (Program program : programs.values()) {
            if (program.getEnrollmentCount() == 0) {
                System.out.println(program.getName() + " - Completed");
            }
        }
    }

    class Program {
        private String name;
        private double revenue;
        private int enrollmentCount;

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

    static class Client {
        private String email;
        private String name;
        private String programName;

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
