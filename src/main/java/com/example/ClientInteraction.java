package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientInteraction {
    private static final Logger logger = Logger.getLogger(ClientInteraction.class.getName());
    private static final List<Client> clients = new ArrayList<>();




    public static boolean addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Added new client: %s", newClient.getName()));
        }
        return true;
    }


    public static List<String> listAllClients() {
        List<String> clientDetails = new ArrayList<>();
        for (Client client : clients) {
            StringBuilder details = new StringBuilder();
            details.append(String.format("%-20s %-30s", client.getName(), client.getEmail()));
            if (client.getMessages().isEmpty()) {
                details.append(" | No messages.");
            } else {
                details.append(" | Messages: ").append(String.join(", ", client.getMessages()));
            }
            clientDetails.add(details.toString());
            logger.info(details.toString());
        }
        return clientDetails;
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
