package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagementTest {

    @BeforeEach
    void setUp() {
        AccountManagement.getInstance();// Singleton instance
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
    void testCreateProfile_invalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountManagement.createProfile("", "invalid@example.com", 25, "Muscle Gain", "None");
        });
    }

    @Test
    void testCreateProfile_invalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountManagement.createProfile("Jane Doe", "invalidemail.com", 25, "Muscle Gain", "None");
        });
    }

    @Test
    void testCreateProfile_invalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountManagement.createProfile("Jane Doe", "jane@example.com", -1, "Muscle Gain", "None");
        });
    }

    @Test
    void testUpdateProfile_existingProfile() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.updateProfile("amrjamhor@example.com", "Amr Jamhor", 32, "Endurance", "Vegan");

        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("amrjamhor@example.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhor", profile.getName());
        assertEquals(32, profile.getAge());
        assertEquals("Endurance", profile.getFitnessGoals());
        assertEquals("Vegan", profile.getDietaryPreferences());
    }

    @Test
    void testUpdateProfile_nonExistingProfile() {
        AccountManagement.updateProfile("nonexistent@example.com", "Amr Jamhor", 32, "Endurance", "Vegan");
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("nonexistent@example.com");
        assertNull(profile);
    }

    @Test
    void testViewProfile_existingProfile() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("amrjamhor@example.com");
        assertNotNull(profile);
    }

    @Test
    void testViewProfile_nonExistingProfile() {
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("nonexistent@example.com");
        assertNull(profile);
    }

    @Test
    void testDeleteProfile_existingProfile() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.deleteProfile("amrjamhor@example.com");
        AccountManagement.ClientProfile profile = AccountManagement.viewProfile("amrjamhor@example.com");
        assertNull(profile);
    }

    @Test
    void testDeleteProfile_nonExistingProfile() {
        AccountManagement.deleteProfile("nonexistent@example.com");

    }

    @Test
    void testListAllProfiles_empty() {
        AccountManagement.listAllProfiles();

    }

    @Test
    void testListAllProfiles_withProfiles() {
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.createProfile("Jane Doe", "jane@example.com", 28, "Muscle Gain", "Non-Vegan");

        AccountManagement.listAllProfiles();

    }
}
