package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClientInteraction {

    private static final Logger logger = Logger.getLogger(ClientInteraction.class.getName());
    private static final List<Client> clients = Collections.synchronizedList(new ArrayList<>());


    private ClientInteraction() {
        throw new UnsupportedOperationException("ClientInteraction class cannot be instantiated.");
    }

    /**
     * Clears all clients from the list.
     */
    public static void clearClients() {
        clients.clear();
        logger.info("All clients have been cleared.");
    }

    /**
     * Adds a new client to the list.
     *
     * @param name  the client's name
     * @param email the client's email
     * @return true if the client is added successfully, false otherwise
     */
    public static boolean addClient(String name, String email) {
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            logger.warning("Invalid client details provided.");
            return false;
        }
        if (clients.stream().anyMatch(client -> client.getEmail().equals(email))) {
            logger.warning(String.format("Client with email %s already exists.", email));
            return false;
        }

        Client newClient = new Client(name, email);
        clients.add(newClient);
        logger.info(String.format("Added new client: %s", newClient.getName()));
        return true;
    }

    /**
     * Lists all clients with their details.
     *
     * @return a list of client details as formatted strings
     */
    public static List<String> listAllClients() {
        List<String> clientDetails = new ArrayList<>();
        synchronized (clients) {
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
        }
        return clientDetails;
    }

    /**
     * Sends a message to a specific client.
     *
     * @param email   the email of the client
     * @param message the message to send
     * @return true if the message is sent successfully, false otherwise
     */
    public static boolean sendMessageToClient(String email, String message) {
        if (message == null || message.trim().isEmpty()) {
            logger.warning("Message cannot be null or empty.");
            return false;
        }

        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add(message);
            logger.info(String.format("Message sent to client %s: %s", client.getName(), message));
            return true;
        } else {
            logger.warning(String.format("Client with email %s not found.", email));
            return false;
        }
    }

    /**
     * Provides a progress report to a specific client.
     *
     * @param email  the email of the client
     * @param report the progress report
     * @return true if the report is sent successfully, false otherwise
     */
    public static boolean provideProgressReport(String email, String report) {
        if (report == null || report.trim().isEmpty()) {
            logger.warning("Progress report cannot be null or empty.");
            return false;
        }

        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.getMessages().add(String.format("Progress report: %s", report));
            logger.info(String.format("Progress report sent to client %s: %s", client.getName(), report));
            return true;
        } else {
            logger.warning(String.format("Client with email %s not found.", email));
            return false;
        }
    }

    /**
     * Inner Client class representing a client.
     */
    public static class Client {
        private final String name;
        private final String email;
        private final List<String> messages;

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
