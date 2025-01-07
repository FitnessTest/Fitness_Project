package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AccountManagementTest {

    @BeforeEach
    void setUp() {
        AccountManagement.getInstance(); // Singleton instance
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
        // Ensure that calling deleteProfile for a non-existing profile does not throw an exception
        AccountManagement.deleteProfile("nonexistent@example.com");

        // If there is a method to list profiles, we can check that the list remains unchanged
        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();
        assertEquals(0, profiles.size(), "No profiles should be present.");
    }

    @Test
    void testListAllProfiles_empty() {
        // Ensure the profile list is empty before calling listAllProfiles()
        AccountManagement.listAllProfiles();

        // Assuming there's a way to get the list of profiles
        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();

        // Assert that the profile list is empty
        assertTrue(profiles.isEmpty(), "Profile list should be empty.");
    }

    @Test
    void testListAllProfiles_withProfiles() {
        // Create profiles
        AccountManagement.createProfile("Amr Jamhor", "amrjamhor@example.com", 30, "Weight Loss", "Vegetarian");
        AccountManagement.createProfile("Jane Doe", "jane@example.com", 28, "Muscle Gain", "Non-Vegan");

        // Call listAllProfiles to ensure the profiles are listed
        AccountManagement.listAllProfiles();

        // Assuming there's a way to get the list of profiles
        List<AccountManagement.ClientProfile> profiles = AccountManagement.getClientProfiles();

        // Assert that the profile list contains the created profiles
        assertEquals(2, profiles.size(), "There should be two profiles.");

        // Optionally, assert that the specific profiles are in the list
        assertTrue(profiles.stream().anyMatch(profile -> "Amr Jamhor".equals(profile.getName())), "Amr Jamhor's profile should be listed.");
        assertTrue(profiles.stream().anyMatch(profile -> "Jane Doe".equals(profile.getName())), "Jane Doe's profile should be listed.");
    }
}
