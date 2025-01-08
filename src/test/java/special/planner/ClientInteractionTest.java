package special.planner;
import com.example.ClientInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        List<String> clients = ClientInteraction.listAllClients();

        assertNotNull(clients, "Client list should not be null");
        assertEquals(2, clients.size(), "There should be exactly 2 clients");

        assertTrue(clients.get(0).contains("Amr Jamhor"), "First client details should contain the name 'Amr Jamhor'");
        assertTrue(clients.get(1).contains("Ihab Habash"), "Second client details should contain the name 'Ihab Habash'");
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
