package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProgressTracking {

    private static final Logger logger = Logger.getLogger(ProgressTracking.class.getName());

    public static class Client {
        private String name;
        private String email;
        private int totalSessions;
        private int completedSessions;
        private List<String> progressMessages;

        public Client(String name, String email, int totalSessions) {
            if (totalSessions <= 0) {
                throw new IllegalArgumentException("Total sessions must be greater than zero.");
            }
            this.name = name;
            this.email = email;
            this.totalSessions = totalSessions;
            this.completedSessions = 0;
            this.progressMessages = new ArrayList<>();
        }

        public double getCompletionRate() {
            return totalSessions == 0 ? 0.0 : ((double) completedSessions / totalSessions) * 100;
        }

        public void addProgressMessage(String message) {
            progressMessages.add(message);
        }

        public List<String> getProgressMessages() {
            return new ArrayList<>(progressMessages);
        }

        public void updateCompletedSessions(int sessionsCompleted) {
            completedSessions += sessionsCompleted;
            if (completedSessions > totalSessions) {
                completedSessions = totalSessions; // Ensure sessions do not exceed total
            }
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return String.format("Client [name=%s, email=%s, completionRate=%.2f%%]", name, email, getCompletionRate());
        }
    }

    private static List<Client> clients;

    public ProgressTracking() {
        clients = new ArrayList<>();
    }

    // Getter method for clients
    public static List<Client> getClients() {
        return new ArrayList<>(clients);
    }

    public static void addClient(Client client) {
        clients.add(client);
        logger.info("Client " + client.getName() + " added successfully.");
    }

    public static void updateCompletedSessions(String clientEmail, int sessionsCompleted) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.updateCompletedSessions(sessionsCompleted);
                logger.info("Updated completed sessions for client " + client.getName() + ".");
                return;
            }
        }
        logger.warning("Client with email " + clientEmail + " not found.");
    }

    public static boolean sendMotivationalReminder(String clientEmail, String reminder) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.addProgressMessage("Motivational Reminder: " + reminder);
                logger.info("Motivational reminder sent to client " + client.getName() + ": " + reminder);
                return true;
            }
        }
        logger.warning("Client with email " + clientEmail + " not found.");
        return false;
    }

    public static void monitorClientProgress() {
        if (clients.isEmpty()) {
            logger.info("No clients available.");
        } else {
            logger.info("Clients and their progress:");
            for (Client client : clients) {
                logger.info(client.toString());
                List<String> progressMessages = client.getProgressMessages();
                if (!progressMessages.isEmpty()) {
                    logger.info("Progress Messages: " + progressMessages);
                } else {
                    logger.info("No progress messages for this client.");
                }
            }
        }
    }
}
