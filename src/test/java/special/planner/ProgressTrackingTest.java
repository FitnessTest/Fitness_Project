package special.planner;

import com.example.ProgressTracking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ProgressTracking class.
 * This class tests the functionality of client management, session tracking, and progress monitoring.
 */
public class ProgressTrackingTest {

    private ProgressTracking progressTracking;

    /**
     * Default constructor for the ProgressTrackingTest class.
     * It initializes the progressTracking object in the setUp() method before each test.
     */
    public ProgressTrackingTest() {
        // No initialization needed as @BeforeEach handles it.
    }
    /**
     * Sets up the test environment by initializing the ProgressTracking object before each test.
     */
    @BeforeEach
    public void setUp() {
        progressTracking = new ProgressTracking();
    }

    /**
     * Tests the addClient() method by adding a new client and verifying the client count.
     */
    @Test
    public void testAddClient() {
        ProgressTracking.Client client = new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", 10);
        progressTracking.addClient(client);
        assertEquals(1, progressTracking.getClients().size());
    }

    /**
     * Tests the updateCompletedSessions() method to ensure the completion rate is updated correctly.
     */
    @Test
    public void testUpdateCompletedSessions() {
        ProgressTracking.Client client = new ProgressTracking.Client("Ihab Habash", "ihab.habash@email.com", 10);
        progressTracking.addClient(client);
        progressTracking.updateCompletedSessions("ihab.habash@email.com", 5);
        assertEquals(50.0, client.getCompletionRate(), 0.1);
    }

    /**
     * Tests the sendMotivationalReminder() method to ensure it sends a reminder and updates the client's progress messages.
     */
    @Test
    public void testSendMotivationalReminder() {
        ProgressTracking.Client client = new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", 10);
        progressTracking.addClient(client);
        boolean result = progressTracking.sendMotivationalReminder("amr.jamhour@email.com", "Keep going!");
        assertTrue(result);
        assertTrue(client.getProgressMessages().contains("Motivational Reminder: Keep going!"));
    }

    /**
     * Tests monitoring client progress and checking the updated completion rate.
     */
    @Test
    public void testMonitorClientProgress() {
        ProgressTracking.Client client = new ProgressTracking.Client("Ihab Habash", "ihab.habash@email.com", 10);
        progressTracking.addClient(client);
        progressTracking.updateCompletedSessions("ihab.habash@email.com", 7);
        progressTracking.monitorClientProgress();
        assertEquals(70.0, client.getCompletionRate(), 0.1);
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the total sessions is invalid.
     */
    @Test
    public void testInvalidTotalSessions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgressTracking.Client("Amr Jamhour", "amr.jamhour@email.com", -5);
        });
    }

    /**
     * Tests that the client is not found when trying to update a non-existing client.
     */
    @Test
    public void testClientNotFoundInUpdate() {
        boolean result = progressTracking.updateCompletedSessions("nonexistent@email.com", 5);
        assertFalse(result, "No client should be found and updated");
    }

    /**
     * Tests the sendMotivationalReminder() method when the client is not found.
     */
    @Test
    public void testSendMotivationalReminderClientNotFound() {
        boolean result = progressTracking.sendMotivationalReminder("nonexistent@email.com", "Keep going!");
        assertFalse(result);
    }

    /**
     * Tests adding and retrieving progress messages for a client.
     */
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
