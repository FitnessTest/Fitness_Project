package com.example;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracking {

    // Client class to hold client details and progress data
    public static class Client {
        private String name;
        private String email;
        private int totalSessions;
        private int completedSessions;
        private List<String> progressMessages;

        // Constructor to initialize client details
        public Client(String name, String email) {
            this.name = name;
            this.email = email;
            this.totalSessions = 0;
            this.completedSessions = 0;
            this.progressMessages = new ArrayList<>();
        }

        // Update attendance and progress for the client
        public void updateProgress(int completedSessions) {
            this.completedSessions = completedSessions;
            this.totalSessions = 10; // Assume there are 10 total sessions per program for simplicity
        }

        // Calculate the completion rate of the client
        public double getCompletionRate() {
            if (totalSessions == 0) {
                return 0.0;
            }
            return (double) completedSessions / totalSessions * 100;
        }

        // Add a progress message (e.g., reminder, recommendation)
        public void addProgressMessage(String message) {
            progressMessages.add(message);
        }

        // Get all progress messages for this client
        public List<String> getProgressMessages() {
            return new ArrayList<>(progressMessages);
        }

        // Get client details
        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Client [name=" + name + ", email=" + email + ", completionRate=" + getCompletionRate() + "%]";
        }
    }

    // List of clients
    private List<Client> clients;

    // Constructor to initialize the ProgressTracking system
    public ProgressTracking() {
        clients = new ArrayList<>();
    }

    // Add a new client to the system
    public void addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        System.out.println("Added new client: " + name);
    }

    // Update client progress (number of completed sessions)
    public boolean updateClientProgress(String clientEmail, int completedSessions) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.updateProgress(completedSessions);
                System.out.println("Updated progress for client " + client.getName() + ": " + completedSessions + " completed sessions.");
                return true;
            }
        }
        System.out.println("Client with email " + clientEmail + " not found.");
        return false;
    }

    // Send a motivational reminder or recommendation to a specific client
    public boolean sendMotivationalReminder(String clientEmail, String reminder) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.addProgressMessage("Motivational Reminder: " + reminder);
                System.out.println("Motivational reminder sent to client " + client.getName() + ": " + reminder);
                return true;
            }
        }
        System.out.println("Client with email " + clientEmail + " not found.");
        return false;
    }

    // List all clients and their progress details
    public void listAllClientsProgress() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.println("Clients and their progress:");
            for (Client client : clients) {
                System.out.println(client);
                List<String> progressMessages = client.getProgressMessages();
                if (!progressMessages.isEmpty()) {
                    System.out.println("Progress Messages: " + progressMessages);
                } else {
                    System.out.println("No progress messages for this client.");
                }
            }
        }
    }

    // Main method for testing the ProgressTracking class
    public static void main(String[] args) {
        ProgressTracking progressTracking = new ProgressTracking();

        // Add some clients
        progressTracking.addClient("Alice Johnson", "alice.johnson@example.com");
        progressTracking.addClient("Bob Smith", "bob.smith@example.com");

        // Update progress for clients
        progressTracking.updateClientProgress("alice.johnson@example.com", 7); // Alice has completed 7 out of 10 sessions
        progressTracking.updateClientProgress("bob.smith@example.com", 5); // Bob has completed 5 out of 10 sessions

        // Send motivational reminders
        progressTracking.sendMotivationalReminder("alice.johnson@example.com", "Keep going Alice! You're almost there!");
        progressTracking.sendMotivationalReminder("bob.smith@example.com", "Great progress Bob! Keep it up!");

        // List all clients and their progress
        progressTracking.listAllClientsProgress();
    }
}
