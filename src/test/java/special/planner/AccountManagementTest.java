package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AccountManagementTest {

    @BeforeEach
    void setUp() {
        // Reset the client profiles list before each test
        AccountManagement.getClientProfiles().clear();
    }

    @Test
    void testCreateProfile_validData() {
        AccountManagement.createProfile("Amr Jamhor", "amrojamhour4@gmail.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("amrojamhour4@gmail.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhor", profile.getName());
        assertEquals("amrojamhour4@gmail.com", profile.getEmail());
        assertEquals(30, profile.getAge());
        assertEquals("Weight Loss", profile.getFitnessGoals());
        assertEquals("Vegetarian", profile.getDietaryPreferences());
    }

    @Test
    void testDeleteProfile_nonExistingProfile() {
        AccountManagement.deleteProfile("nonexistent@example.com");
        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();
        assertTrue(profiles.isEmpty(), "Profile list should be empty after deletion attempt.");
    }

    @Test
    void testListAllProfiles_empty() {
        AccountManagement.listAllProfiles();
        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();
        assertTrue(profiles.isEmpty(), "No profiles should be present.");
    }

    @Test
    void testListAllProfiles_withProfiles() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.createProfile("Jane Doe", "jane@example.com", 28, "Muscle Gain", "Non-Vegan");
        AccountManagement.listAllProfiles();

        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();
        assertEquals(2, profiles.size(), "There should be two profiles.");
    }

    @Test
    void testDeleteProfile_existingProfile() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.deleteProfile("amrjamhor@example.com");
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("amrjamhor@example.com");
        assertNull(profile, "Profile should be null after deletion.");
    }
}
