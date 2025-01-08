package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages notifications and updates for clients.
 * It allows adding clients, notifying them of schedule changes, and announcing new programs or offers.
 */
public class NotificationsAndUpdates {

    private static final Logger LOGGER = Logger.getLogger(NotificationsAndUpdates.class.getName());

    /**
     * Represents a client in the system with a name, email, and a list of notifications.
     */
    public static class Client {
        private String name;
        private String email;
        private List<String> notifications;

        /**
         * Constructs a new Client with a given name and email.
         *
         * @param name  The name of the client.
         * @param email The email of the client.
         */
        public Client(String name, String email) {
            this.name = name;
            this.email = email;
            this.notifications = new ArrayList<>();
        }

        /**
         * Adds a notification to the client's list of notifications.
         *
         * @param notification The notification message to add.
         */
        public void addNotification(String notification) {
            notifications.add(notification);
        }

        /**
         * Gets a copy of the list of notifications for the client.
         *
         * @return A list of notifications for the client.
         */
        public List<String> getNotifications() {
            return new ArrayList<>(notifications);
        }

        /**
         * Gets the name of the client.
         *
         * @return The name of the client.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the email of the client.
         *
         * @return The email of the client.
         */
        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Client [name=" + name + ", email=" + email + "]";
        }
    }

    /**
     * List of all clients in the system.
     */
    public static List<Client> clients;

    /**
     * Constructs a new NotificationsAndUpdates instance, initializing the clients list.
     */
    public NotificationsAndUpdates() {
        clients = new ArrayList<>();
    }

    /**
     * Adds a new client to the system.
     *
     * @param name  The name of the client.
     * @param email The email of the client.
     */
    public static void addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        LOGGER.log(Level.INFO, "Added new client: {0}", name);
    }

    /**
     * Notifies all clients about a schedule change for a program.
     *
     * @param programTitle The title of the program with the schedule change.
     * @param newSchedule  The new schedule for the program.
     */
    public static void notifyScheduleChange(String programTitle, String newSchedule) {
        String notification = "The schedule for the program \"" + programTitle + "\" has been changed. The new schedule is: " + newSchedule;
        for (Client client : clients) {
            client.addNotification(notification);
            LOGGER.log(Level.INFO, "Notified {0} about the schedule change.", client.getName());
        }
    }

    /**
     * Announces a new program or special offer to all clients.
     *
     * @param announcement The announcement message.
     */
    public static void announceNewProgramOrOffer(String announcement) {
        for (Client client : clients) {
            client.addNotification("Announcement: " + announcement);
            LOGGER.log(Level.INFO, "Notified {0} about the new program or special offer.", client.getName());
        }
    }

    /**
     * Lists all clients and their notifications.
     * Logs the clients' details along with their notifications.
     */
    public static void listAllClientsNotifications() {
        if (clients.isEmpty()) {
            LOGGER.log(Level.INFO, "No clients available.");
        } else {
            LOGGER.log(Level.INFO, "Clients and their notifications:");
            for (Client client : clients) {
                LOGGER.log(Level.INFO, "Client Name: {0}, Email: {1}", new Object[]{client.getName(), client.getEmail()});

                List<String> notifications = client.getNotifications();
                if (!notifications.isEmpty()) {
                    LOGGER.log(Level.INFO, "Notifications for {0}:", client.getName());
                    for (String notification : notifications) {
                        LOGGER.log(Level.INFO, "  - {0}", notification);
                    }
                } else {
                    LOGGER.log(Level.INFO, "No notifications for this client.");
                }
            }
        }
    }
}
