package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientInteraction {

    private static List<Client> clients = new ArrayList<>();

    // Adds a new client to the list
    public static boolean addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        System.out.println("Added new client: " + newClient.getName());
        return true;
    }

    // Lists all clients and their messages
    public static void listAllClients() {
        System.out.println("Clients and their messages:");
        for (Client client : clients) {
            System.out.printf("%-20s %-30s%n", client.getName(), client.getEmail());
            System.out.println("------------------------------------------------------------");
            if (client.getMessages().isEmpty()) {
                System.out.println("No messages for this client.");
            } else {
                for (String message : client.getMessages()) {
                    System.out.println(message);
                }
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    // Sends a message to a client by email
    public static boolean sendMessageToClient(String email, String message) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add(message);
            System.out.println("Message sent to client " + client.getName() + ": " + message);
            return true;
        } else {
            System.out.println("Client with email " + email + " not found.");
            return false;
        }
    }

    // Sends a progress report to a client by email
    public static boolean provideProgressReport(String email, String report) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add("Progress report: " + report);
            System.out.println("Progress report sent to client " + client.getName() + ": " + report);
            return true;
        } else {
            System.out.println("Client with email " + email + " not found.");
            return false;
        }
    }

    // A client class with basic properties and messages
    public static class Client {
        private String name;
        private String email;
        private List<String> messages;

        public Client(String name, String email) {
            this.name = name;
            this.email = email;
            this.messages = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public List<String> getMessages() {
            return messages;
        }
    }
}
