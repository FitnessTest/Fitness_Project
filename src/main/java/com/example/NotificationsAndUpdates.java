package com.example;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAndUpdates {


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


    private static List<Client> clients;


    public NotificationsAndUpdates() {
        clients = new ArrayList<>();
    }


    public static void addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        System.out.println("Added new client: " + name);
    }


    public static void notifyScheduleChange(String programTitle, String newSchedule) {
        String notification = "The schedule for the program \"" + programTitle + "\" has been changed. The new schedule is: " + newSchedule;
        for (Client client : clients) {
            client.addNotification(notification);
            System.out.println("Notified " + client.getName() + " about the schedule change.");
        }
    }


    public static void announceNewProgramOrOffer(String announcement) {
        for (Client client : clients) {
            client.addNotification("Announcement: " + announcement);
            System.out.println("Notified " + client.getName() + " about the new program or special offer.");
        }
    }

    public static void listAllClientsNotifications() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.println("Clients and their notifications:");
            for (Client client : clients) {

                System.out.printf("%-20s %-30s%n", "Name", "Email");
                System.out.printf("%-20s %-30s%n", client.getName(), client.getEmail());
                System.out.println("------------------------------------------------------------");


                List<String> notifications = client.getNotifications();
                if (!notifications.isEmpty()) {
                    System.out.println("Notifications:");
                    for (String notification : notifications) {
                        System.out.println("  - " + notification);
                    }
                } else {
                    System.out.println("No notifications for this client.");
                }
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
