package special.planner;

import com.example.ProgressTracking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgressTrackingTest {

    private ProgressTracking progressTracking;

    @BeforeEach
    public void setUp() {
        progressTracking = new ProgressTracking();
    }

    @Test
    public void testAddClient() {
        ProgressTracking.Client client = new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", 10);
        progressTracking.addClient(client);
        assertEquals(1, progressTracking.getClients().size());
    }
    @Test
    public void testUpdateCompletedSessions() {
        ProgressTracking.Client client = new ProgressTracking.Client("Ihab Habash", "ihab.habash@email.com", 10);
        progressTracking.addClient(client);
        progressTracking.updateCompletedSessions("ihab.habash@email.com", 5);


        assertEquals(50.0, client.getCompletionRate(), 0.1);
    }
    @Test
    public void testSendMotivationalReminder() {
        ProgressTracking.Client client = new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", 10);
        progressTracking.addClient(client);
        boolean result = progressTracking.sendMotivationalReminder("amr.jamhour@email.com", "Keep going!");
        assertTrue(result);
        assertTrue(client.getProgressMessages().contains("Motivational Reminder: Keep going!"));
    }

    @Test
    public void testMonitorClientProgress() {
        ProgressTracking.Client client = new ProgressTracking.Client("Ihab Habash", "ihab.habash@email.com", 10);
        progressTracking.addClient(client);
        progressTracking.monitorClientProgress();
    }

    @Test
    public void testInvalidTotalSessions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", -5);
        });
    }

    @Test
    public void testClientNotFoundInUpdate() {
        progressTracking.updateCompletedSessions("nonexistent@email.com", 5); // No client should be found
    }

    @Test
    public void testSendMotivationalReminderClientNotFound() {
        boolean result = progressTracking.sendMotivationalReminder("nonexistent@email.com", "Keep going!");
        assertFalse(result);
    }

    @Test
    public void testProgressMessages() {
        ProgressTracking.Client client = new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", 10);
        progressTracking.addClient(client);
        client.addProgressMessage("Completed 2 sessions.");
        List<String> messages = client.getProgressMessages();
        assertEquals(1, messages.size());
        assertEquals("Completed 2 sessions.", messages.get(0));
    }
}
