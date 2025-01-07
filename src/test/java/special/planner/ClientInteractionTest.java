package special.planner;

import com.example.ClientInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientInteractionTest {

    @BeforeEach
    void setUp() {
        ClientInteraction clientInteraction = new ClientInteraction();
    }

    @Test
    void testAddClient() {
        boolean result = ClientInteraction.addClient("Amr Jamhor", "amrojamhour4@gmail.com");
        assertTrue(result, "Client should be added successfully");
    }

    @Test
    void testListAllClients_WithProfiles() {
        ClientInteraction.addClient("Amr Jamhor", "amrojamhour4@gmail.com");
        ClientInteraction.addClient("Ihab Habash", "kebab83@gmail.com");



        ClientInteraction.listAllClients();
    }

    @Test
    void testSendMessageToClient_ClientFound() {
        ClientInteraction.addClient("Amr Jamhor", "amrojamhour4@gmail.com");

        boolean messageSent = ClientInteraction.sendMessageToClient("amrojamhour4@gmail.com", "Hello Amr");
        assertTrue(messageSent, "Message should be sent successfully to the client");
    }

    @Test
    void testSendMessageToClient_ClientNotFound() {
        boolean messageSent = ClientInteraction.sendMessageToClient("nonexistent@example.com", "Hello World");
        assertFalse(messageSent, "Message should not be sent to a non-existent client");
    }

    @Test
    void testProvideProgressReport_ClientFound() {
        ClientInteraction.addClient("Amr Jamhor", "amrojamhour4@gmail.com");

        boolean reportSent = ClientInteraction.provideProgressReport("amrojamhour4@gmail.com", "Progress report here");
        assertTrue(reportSent, "Progress report should be sent successfully to the client");
    }

    @Test
    void testProvideProgressReport_ClientNotFound() {
        boolean reportSent = ClientInteraction.provideProgressReport("nonexistent@example.com", "Progress report here");
        assertFalse(reportSent, "Progress report should not be sent to a non-existent client");
    }
}
