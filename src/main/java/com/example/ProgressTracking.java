package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class tracks the progress of clients in a fitness program, including
 * monitoring completed sessions and sending motivational reminders.
 */
public class ProgressTracking {

    // Logger to log progress tracking information
    private static final Logger logger = Logger.getLogger(ProgressTracking.class.getName());

    /**
     * The Client class holds information about a client, including their name, email,
     * total sessions, completed sessions, and progress messages.
     */
    public static class Client {
        private String name;
        private String email;
        private int totalSessions;
        private int completedSessions;
        private List<String> progressMessages;

        /**
         * Constructor for initializing a client with their name, email, and total sessions.
         *
         * @param name Client's name
         * @param email Client's email address
         * @param totalSessions Total number of sessions in the program
         * @throws IllegalArgumentException if totalSessions is less than or equal to zero
         */
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

        /**
         * Returns the completion rate of the client based on the total sessions and completed sessions.
         *
         * @return Completion rate as a percentage
         */
        public double getCompletionRate() {
            return totalSessions == 0 ? 0.0 : ((double) completedSessions / totalSessions) * 100;
        }

        /**
         * Adds a progress message for the client.
         *
         * @param message The progress message to add
         */
        public void addProgressMessage(String message) {
            progressMessages.add(message);
        }

        /**
         * Retrieves the list of progress messages for the client.
         *
         * @return A list of progress messages
         */
        public List<String> getProgressMessages() {
            return new ArrayList<>(progressMessages);
        }

        /**
         * Updates the number of completed sessions for the client.
         *
         * @param sessionsCompleted The number of sessions completed to add
         */
        public void updateCompletedSessions(int sessionsCompleted) {
            completedSessions += sessionsCompleted;
            if (completedSessions > totalSessions) {
                completedSessions = totalSessions; // Ensure sessions do not exceed total
            }
        }

        /**
         * Getter for the client's name.
         *
         * @return The name of the client
         */
        public String getName() {
            return name;
        }

        /**
         * Getter for the client's email.
         *
         * @return The email of the client
         */
        public String getEmail() {
            return email;
        }

        /**
         * Returns a string representation of the client.
         *
         * @return A string showing the client's name, email, and completion rate
         */
        @Override
        public String toString() {
            return String.format("Client [name=%s, email=%s, completionRate=%.2f%%]", name, email, getCompletionRate());
        }
    }

    // List of clients being tracked
    private static List<Client> clients;

    /**
     * Constructor to initialize the ProgressTracking system and the clients list.
     */
    public ProgressTracking() {
        clients = new ArrayList<>();
    }

    /**
     * Getter method to retrieve the list of all clients.
     *
     * @return A list of all clients
     */
    public static List<Client> getClients() {
        return new ArrayList<>(clients);
    }

    /**
     * Adds a new client to the progress tracking system.
     *
     * @param client The client to add
     */
    public static void addClient(Client client) {
        clients.add(client);
        logger.info("Client " + client.getName() + " added successfully.");
    }

    /**
     * Updates the completed sessions for a client based on their email address.
     *
     * @param clientEmail The email of the client to update
     * @param sessionsCompleted The number of sessions completed to add
     * @return True if the update is successful, false if client is not found
     */
    public static boolean updateCompletedSessions(String clientEmail, int sessionsCompleted) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.updateCompletedSessions(sessionsCompleted);
                logger.info("Updated completed sessions for client " + client.getName() + ".");
                return true; // Return true when the client's sessions are updated successfully
            }
        }
        logger.warning("Client with email " + clientEmail + " not found.");
        return false; // Return false when the client is not found
    }

    /**
     * Sends a motivational reminder to a client based on their email address.
     *
     * @param clientEmail The email of the client to send the reminder to
     * @param reminder The motivational message to send
     * @return True if the reminder is successfully sent, false if client is not found
     */
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

    /**
     * Monitors and logs the progress of all clients.
     */
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
