package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientInteraction {
    private static final Logger logger = Logger.getLogger(ClientInteraction.class.getName());
    private static final List<Client> clients = new ArrayList<>();


    public ClientInteraction() {
    }


    public static boolean addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Added new client: %s", newClient.getName()));
        }
        return true;
    }


    public static void listAllClients() {

        if (logger.isLoggable(Level.INFO)) {
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
    }


    public static boolean sendMessageToClient(String email, String message) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add(message);

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Message sent to client %s: %s", client.getName(), message));
            }
            return true;
        } else {

            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Client with email %s not found.", email));
            }
            return false;
        }
    }


    public static boolean provideProgressReport(String email, String report) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add(String.format("Progress report: %s", report));

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Progress report sent to client %s: %s", client.getName(), report));
            }
            return true;
        } else {

            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Client with email %s not found.", email));
            }
            return false;
        }
    }

    // Inner Client class to represent a client
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
