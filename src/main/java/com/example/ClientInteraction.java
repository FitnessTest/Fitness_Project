package com.example;


import java.util.ArrayList;
import java.util.List;

public class ClientInteraction {


    public static class Client {
        private String name;
        private String email;
        private List<String> messages;


        public Client(String name, String email) {
            this.name = name;
            this.email = email;
            this.messages = new ArrayList<>();
        }


        public void addMessage(String message) {
            messages.add(message);
        }


        public List<String> getMessages() {
            return new ArrayList<>(messages);
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


    private List<Client> clients;


    public ClientInteraction() {
        clients = new ArrayList<>();
    }


    public void addClient(String name, String email) {
        Client newClient = new Client(name, email);
        clients.add(newClient);
        System.out.println("Added new client: " + name);
    }

    // Send a message to a specific client
    public boolean sendMessageToClient(String clientEmail, String message) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.addMessage(message);
                System.out.println("Message sent to client " + client.getName() + ": " + message);
                return true;
            }
        }
        System.out.println("Client with email " + clientEmail + " not found.");
        return false;
    }


    public boolean provideProgressReport(String clientEmail, String report) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(clientEmail)) {
                client.addMessage("Progress Report: " + report);
                System.out.println("Progress report sent to client " + client.getName() + ": " + report);
                return true;
            }
        }
        System.out.println("Client with email " + clientEmail + " not found.");
        return false;
    }


    public void listAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.println("Clients and their messages:");
            for (Client client : clients) {
                // Print client info in a table-like format
                System.out.printf("%-20s %-30s%n", "Name", "Email");
                System.out.printf("%-20s %-30s%n", client.getName(), client.getEmail());
                System.out.println("------------------------------------------------------------");

                // Print messages or a no message notification
                List<String> messages = client.getMessages();
                if (!messages.isEmpty()) {
                    System.out.println("Messages:");
                    for (String message : messages) {
                        System.out.println("  - " + message);
                    }
                } else {
                    System.out.println("No messages for this client.");
                }
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}