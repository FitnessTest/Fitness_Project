package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagementTest {

    private AccountManagement accountManagement;

    @BeforeEach
    void setUp() {
        accountManagement = AccountManagement.getInstance();
    }
    @Test
    void testCreateProfile() {
        accountManagement.deleteProfile("amr.jamhour@example.com");


        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Lose weight", "Vegan");


        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");


        assertNotNull(profile);
        assertEquals("Amr Jamhour", profile.getName());
        assertEquals("amr.jamhour@example.com", profile.getEmail());
        assertEquals(30, profile.getAge());
        assertEquals("Lose weight", profile.getFitnessGoals());
        assertEquals("Vegan", profile.getDietaryPreferences());
    }

    @Test
    void testCreateProfileWithEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("", "invalid.email@example.com", 25, "Lose weight", "None");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateProfileWithInvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Ihab Habash", "invalidemail", 25, "Lose weight", "None");
        });
        assertEquals("Invalid email address", exception.getMessage());
    }

    @Test
    void testCreateProfileWithNegativeAge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Ihab Habash", "ihab.habash@example.com", -5, "Lose weight", "None");
        });
        assertEquals("Age must be a positive number", exception.getMessage());
    }

    @Test
    void testUpdateProfile() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 25, "Gain muscle", "Vegetarian");
        accountManagement.updateProfile("amr.jamhour@example.com", "Amr Jamhour Updated", 26, "Lose fat", "No preferences");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");

        assertNotNull(profile);
        assertEquals("Amr Jamhour Updated", profile.getName());
        assertEquals(26, profile.getAge());
        assertEquals("Lose fat", profile.getFitnessGoals());
        assertEquals("No preferences", profile.getDietaryPreferences());
    }

    @Test
    void testUpdateProfileNotFound() {
        accountManagement.createProfile("Ihab Habash", "ihab.habash@example.com", 35, "General Fitness", "No preferences");

        accountManagement.updateProfile("nonexistent@example.com", "Updated Name", 30, "New Goal", "New Preference");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("ihab.habash@example.com");
        assertNotNull(profile);
        assertEquals("Ihab Habash", profile.getName());
    }

    @Test
    void testDeleteProfile() {
        accountManagement.createProfile("Ihab Habash", "ihab.habash@example.com", 35, "General Fitness", "No preferences");
        accountManagement.deleteProfile("ihab.habash@example.com");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("ihab.habash@example.com");

        assertNull(profile);
    }

    @Test
    void testDeleteProfileNotFound() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 40, "Strength training", "Gluten-free");

        accountManagement.deleteProfile("nonexistent@example.com");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");

        assertNotNull(profile);
    }

    @Test
    void testViewProfileNotFound() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 40, "Strength training", "Gluten-free");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("nonexistent@example.com");

        assertNull(profile);
    }

    @Test
    void testListAllProfiles() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 28, "Cardio", "Vegetarian");
        accountManagement.createProfile("Ihab Habash", "ihab.habash@example.com", 33, "Yoga", "No preferences");

        accountManagement.listAllProfiles();
    }

    @Test
    void testListAllProfilesWhenEmpty() {
        accountManagement.listAllProfiles();
    }

    @Test
    void testProfileValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Ihab Habash", "invalidemail", 30, "Lose weight", "No preferences");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Amr Jamhour", "amr.habash@example.com", -1, "Gain muscle", "No preferences");
        });
    }

    @Test
    void testProfileCreationWithDefaultValues() {
        accountManagement.createProfile("Default Profile", "default@example.com", 22, "", "");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("default@example.com");

        assertNotNull(profile);

        assertEquals("Not specified", profile.getDietaryPreferences());
    }

    @Test
    void testProfileWithNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile(null, "null.name@example.com", 25, "Lose weight", "None");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testProfileWithNullEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Null Email", null, 25, "Lose weight", "None");
        });
        assertEquals("Invalid email address", exception.getMessage());
    }

    @Test
    void testProfileWithNullAge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountManagement.createProfile("Null Age", "null.age@example.com", 0, "Lose weight", "None");
        });
        assertEquals("Age must be a positive number", exception.getMessage());
    }
}
