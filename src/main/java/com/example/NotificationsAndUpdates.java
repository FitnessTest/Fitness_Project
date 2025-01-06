package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationsAndUpdates {

    private static final Logger LOGGER = Logger.getLogger(NotificationsAndUpdates.class.getName());

    public static class Client {
        private String name;
        private String email;
        private List<String> notifications;

        public Client(String name, String email) {
            this.name = name;
            this.email = email;
            this.notifications = new ArrayList<>();
        }

        public void addNotification(String notification) {
            notifications.add(notification);
        }

        public List<String> getNotifications() {
            return new ArrayList<>(notifications);
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Client [name=" + name + ", email=" + email + "]";
        }
    }

    public static List<Client> clients;

    public NotificationsAndUpdates() {
        clients = new ArrayList<>();
    }

    public static void addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        LOGGER.log(Level.INFO, "Added new client: {0}", name);
    }

    public static void notifyScheduleChange(String programTitle, String newSchedule) {
        String notification = "The schedule for the program \"" + programTitle + "\" has been changed. The new schedule is: " + newSchedule;
        for (Client client : clients) {
            client.addNotification(notification);
            LOGGER.log(Level.INFO, "Notified {0} about the schedule change.", client.getName());
        }
    }

    public static void announceNewProgramOrOffer(String announcement) {
        for (Client client : clients) {
            client.addNotification("Announcement: " + announcement);
            LOGGER.log(Level.INFO, "Notified {0} about the new program or special offer.", client.getName());
        }
    }

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
