package special.planner;

import com.example.ProgressTrackingClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProgressTrackingClientTest {

    @BeforeEach
    void setup() {
        // Clear the progress map before each test to ensure clean tests
        ProgressTrackingClient.getClientProgressMap().clear();
    }

    @Test
    void testUpdateClientProgress_validInput() {
        ProgressTrackingClient.updateClientProgress("1", 70.0, 1.75, 5);
        assertTrue(ProgressTrackingClient.getClientProgressMap().containsKey("1"));
        assertEquals(70.0, ProgressTrackingClient.getClientProgressMap().get("1").getWeight());
        assertEquals(1.75, ProgressTrackingClient.getClientProgressMap().get("1").getHeight());
        assertEquals(5, ProgressTrackingClient.getClientProgressMap().get("1").getAttendanceCount());
    }

    @Test
    void testUpdateClientProgress_invalidHeight() {
        // Test for invalid height (<= 0)
        assertThrows(IllegalArgumentException.class, () -> {
            ProgressTrackingClient.updateClientProgress("2", 70.0, 0.0, 5);
        });
    }

    @Test
    void testShowClientProgress_existingClient() {
        ProgressTrackingClient.updateClientProgress("3", 75.0, 1.80, 10);
        assertDoesNotThrow(() -> ProgressTrackingClient.showClientProgress("3"));
    }

    @Test
    void testShowClientProgress_nonExistentClient() {
        assertDoesNotThrow(() -> ProgressTrackingClient.showClientProgress("non-existent"));
    }

    @Test
    void testAddAchievement() {
        ProgressTrackingClient.updateClientProgress("4", 80.0, 1.85, 15);
        ProgressTrackingClient.addAchievement("4", "Completed 10K Run", "2025-01-01");
        assertTrue(ProgressTrackingClient.getClientProgressMap().get("4").getAchievements().containsKey("Completed 10K Run"));
    }

    @Test
    void testAddAchievement_noClient() {
        // Test when client does not exist
        ProgressTrackingClient.addAchievement("5", "Completed Marathon", "2025-01-01");
        assertFalse(ProgressTrackingClient.getClientProgressMap().containsKey("5"));
    }

    @Test
    void testRemoveAchievement() {
        ProgressTrackingClient.updateClientProgress("6", 90.0, 1.95, 20);
        ProgressTrackingClient.addAchievement("6", "Completed 5K Run", "2025-01-01");
        ProgressTrackingClient.removeAchievement("6", "Completed 5K Run");
        assertFalse(ProgressTrackingClient.getClientProgressMap().get("6").getAchievements().containsKey("Completed 5K Run"));
    }

    @Test
    void testRemoveAchievement_nonExistentAchievement() {
        ProgressTrackingClient.updateClientProgress("7", 60.0, 1.70, 8);
        ProgressTrackingClient.addAchievement("7", "Completed 5K Run", "2025-01-01");
        // Attempt to remove a non-existent achievement
        ProgressTrackingClient.removeAchievement("7", "Completed Half Marathon");
        assertTrue(ProgressTrackingClient.getClientProgressMap().get("7").getAchievements().containsKey("Completed 5K Run"));
    }

    @Test
    void testListAchievements_existingAchievements() {
        ProgressTrackingClient.updateClientProgress("8", 85.0, 1.80, 25);
        ProgressTrackingClient.addAchievement("8", "Completed 10K Run", "2025-01-01");
        ProgressTrackingClient.addAchievement("8", "Completed Marathon", "2025-02-01");
        assertDoesNotThrow(() -> ProgressTrackingClient.listAchievements("8"));
    }

    @Test
    void testListAchievements_noAchievements() {
        ProgressTrackingClient.updateClientProgress("9", 65.0, 1.65, 30);
        assertDoesNotThrow(() -> ProgressTrackingClient.listAchievements("9"));
    }

    @Test
    void testListAchievements_nonExistentClient() {
        assertDoesNotThrow(() -> ProgressTrackingClient.listAchievements("non-existent"));
    }
}
