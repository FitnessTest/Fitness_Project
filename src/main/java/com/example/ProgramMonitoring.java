package com.example;


import java.util.*;

public class ProgramMonitoring {

    private Map<String, Program> programs;
    private Map<String, Client> clients;


    public ProgramMonitoring() {
        programs = new HashMap<>();
        clients = new HashMap<>();

        programs.put("Java Programming", new Program("Java Programming", 1500, 50));
        programs.put("Data Science", new Program("Data Science", 2000, 40));
        programs.put("Web Development", new Program("Web Development", 1200, 30));

        clients.put("john.doe@gmail.com", new Client("john.doe@gmail.com", "John Doe", "Java Programming"));
        clients.put("jane.doe@gmail.com", new Client("jane.doe@gmail.com", "Jane Doe", "Data Science"));
        clients.put("alex.smith@gmail.com", new Client("alex.smith@gmail.com", "Alex Smith", "Web Development"));
    }


    public List<String> viewMostPopularPrograms() {
        List<Program> programList = new ArrayList<>(programs.values());
        programList.sort(Comparator.comparingInt(Program::getEnrollmentCount).reversed());

        List<String> result = new ArrayList<>();
        for (Program program : programList) {
            result.add(program.getName() + " - Enrolled: " + program.getEnrollmentCount());
        }
        return result;
    }


    public void generateReports() {
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


    public void trackProgramsStatus() {
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


    class Client {
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

    public static void main(String[] args) {
        ProgramMonitoring monitoring = new ProgramMonitoring();


        System.out.println("Most Popular Programs:");
        for (String program : monitoring.viewMostPopularPrograms()) {
            System.out.println(program);
        }


        monitoring.generateReports();


        monitoring.trackProgramsStatus();
    }
}
