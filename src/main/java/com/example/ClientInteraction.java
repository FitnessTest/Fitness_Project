package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ClientInteraction {

    private static final Logger logger = Logger.getLogger(ClientInteraction.class.getName());
    private static List<Client> clients = new ArrayList<>();

    // Adds a new client to the list
    public static boolean addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        logger.info("Added new client: " + newClient.getName());
        return true;
    }

    // Lists all clients and their messages
    public static void listAllClients() {
        logger.info("Clients and their messages:");
        for (Client client : clients) {
            logger.info(String.format("%-20s %-30s", client.getName(), client.getEmail()));
            logger.info("------------------------------------------------------------");
            if (client.getMessages().isEmpty()) {
                logger.info("No messages for this client.");
            } else {
                for (String message : client.getMessages()) {
                    logger.info(message);
                }
            }
            logger.info("------------------------------------------------------------");
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
            logger.info("Message sent to client " + client.getName() + ": " + message);
            return true;
        } else {
            logger.warning("Client with email " + email + " not found.");
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
            logger.info("Progress report sent to client " + client.getName() + ": " + report);
            return true;
        } else {
            logger.warning("Client with email " + email + " not found.");
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
