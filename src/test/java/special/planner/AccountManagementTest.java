package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagementTest {

    private AccountManagement accountManagement;

    @BeforeEach
    void setUp() {
        accountManagement = AccountManagement.getInstance();  // Singleton instance
    }

    @Test
    void testCreateProfile_validData() {
        accountManagement.createProfile("Amr Jamhor", "amrojamhour4@gmail.com", 30, "Weight Loss", "Vegetarian");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amrojamhour4@gmail.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhor", profile.getName());
        assertEquals("amrojamhour4@gmail.com", profile.getEmail());
        assertEquals(30, profile.getAge());
        assertEquals("Weight Loss", profile.getFitnessGoals());
        assertEquals("Vegetarian", profile.getDietaryPreferences());
    }

    @Test
    void testCreateProfile_invalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("", "invalid@example.com", 25, "Muscle Gain", "None");
        });
    }

    @Test
    void testCreateProfile_invalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Jane Doe", "invalidemail.com", 25, "Muscle Gain", "None");
        });
    }

    @Test
    void testCreateProfile_invalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Jane Doe", "jane@example.com", -1, "Muscle Gain", "None");
        });
    }

    @Test
    void testUpdateProfile_existingProfile() {
        accountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        accountManagement.updateProfile("amrjamhor@example.com", "Amr Jamhor", 32, "Endurance", "Vegan");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amrjamhor@example.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhor", profile.getName());
        assertEquals(32, profile.getAge());
        assertEquals("Endurance", profile.getFitnessGoals());
        assertEquals("Vegan", profile.getDietaryPreferences());
    }

    @Test
    void testUpdateProfile_nonExistingProfile() {
        accountManagement.updateProfile("nonexistent@example.com", "Amr Jamhor", 32, "Endurance", "Vegan");
        // Verify the profile was not updated
        AccountManagement.ClientProfile profile = accountManagement.viewProfile("nonexistent@example.com");
        assertNull(profile);
    }

    @Test
    void testViewProfile_existingProfile() {
        accountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amrjamhor@example.com");
        assertNotNull(profile);
    }

    @Test
    void testViewProfile_nonExistingProfile() {
        AccountManagement.ClientProfile profile = accountManagement.viewProfile("nonexistent@example.com");
        assertNull(profile);
    }

    @Test
    void testDeleteProfile_existingProfile() {
        accountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        accountManagement.deleteProfile("amrjamhor@example.com");
        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amrjamhor@example.com");
        assertNull(profile);
    }

    @Test
    void testDeleteProfile_nonExistingProfile() {
        accountManagement.deleteProfile("nonexistent@example.com");
        // No exception, just ensure the method works silently
    }

    @Test
    void testListAllProfiles_empty() {
        accountManagement.listAllProfiles();
        // Ensure no profiles are logged
    }

    @Test
    void testListAllProfiles_withProfiles() {
        accountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        accountManagement.createProfile("Jane Doe", "jane@example.com", 28, "Muscle Gain", "Non-Vegan");

        accountManagement.listAllProfiles();
        // Manually check logs to verify both profiles are listed
    }
}
