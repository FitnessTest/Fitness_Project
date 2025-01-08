package special.planner;

import com.example.AccountManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManagementTest {

    private AccountManagement accountManagement;
    private static final Logger logger = Logger.getLogger(AccountManagement.class.getName());

    @BeforeEach
    public void setUp() {
        // Clear any existing profiles before each test
        accountManagement = AccountManagement.getInstance();
        accountManagement.getClientProfiles().clear();

        // Set logger to log to console for tests
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    @Test
    public void testCreateProfile() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        // Check if the profile was created
        List<AccountManagement.ClientProfile> profiles = accountManagement.getClientProfiles();
        assertEquals(1, profiles.size());
        assertEquals("Amr Jamhour", profiles.get(0).getName());
        assertEquals("amr.jamhour@example.com", profiles.get(0).getEmail());
    }

    @Test
    public void testCreateProfileInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                accountManagement.createProfile("Amr Jamhour", "invalid-email", 30, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testCreateProfileInvalidAge() {
        assertThrows(IllegalArgumentException.class, () ->
                accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", -1, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testUpdateProfile() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        accountManagement.updateProfile("amr.jamhour@example.com", "Ihab Habash", 31, "Muscle gain", "Non-Vegetarian");

        AccountManagement.ClientProfile updatedProfile = accountManagement.viewProfile("amr.jamhour@example.com");
        assertNotNull(updatedProfile);
        assertEquals("Ihab Habash", updatedProfile.getName());
        assertEquals(31, updatedProfile.getAge());
        assertEquals("Muscle gain", updatedProfile.getFitnessGoals());
        assertEquals("Non-Vegetarian", updatedProfile.getDietaryPreferences());
    }

    @Test
    public void testUpdateProfileNotFound() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        accountManagement.updateProfile("nonexistent@example.com", "Ihab Habash", 28, "Endurance", "Vegan");

        // Ensure the profile was not updated
        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhour", profile.getName());
    }

    @Test
    public void testViewProfile() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");
        assertNotNull(profile);
        assertEquals("Amr Jamhour", profile.getName());
    }

    @Test
    public void testViewProfileNotFound() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("nonexistent@example.com");
        assertNull(profile);
    }

    @Test
    public void testDeleteProfile() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");

        accountManagement.deleteProfile("amr.jamhour@example.com");

        AccountManagement.ClientProfile profile = accountManagement.viewProfile("amr.jamhour@example.com");
        assertNull(profile);
    }

    @Test
    public void testListAllProfiles() {
        accountManagement.createProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian");
        accountManagement.createProfile("Ihab Habash", "ihab.habash@example.com", 28, "Muscle gain", "Non-Vegetarian");

        // Retrieve the list of profiles
        List<AccountManagement.ClientProfile> profiles = accountManagement.getClientProfiles();

        // Assert the number of profiles in the list
        assertEquals(2, profiles.size());

        // Assert the details of the first profile
        assertEquals("Amr Jamhour", profiles.get(0).getName());
        assertEquals("amr.jamhour@example.com", profiles.get(0).getEmail());

        // Assert the details of the second profile
        assertEquals("Ihab Habash", profiles.get(1).getName());
        assertEquals("ihab.habash@example.com", profiles.get(1).getEmail());
    }

    @Test
    public void testClientProfileConstructorNullName() {
        assertThrows(IllegalArgumentException.class, () ->
                new AccountManagement.ClientProfile(null, "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testClientProfileConstructorEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                new AccountManagement.ClientProfile("", "amr.jamhour@example.com", 30, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testClientProfileConstructorInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new AccountManagement.ClientProfile("Amr Jamhour", "invalid-email", 30, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testClientProfileConstructorNegativeAge() {
        assertThrows(IllegalArgumentException.class, () ->
                new AccountManagement.ClientProfile("Amr Jamhour", "amr.jamhour@example.com", -1, "Weight loss", "Vegetarian"));
    }

    @Test
    public void testClientProfileConstructorEmptyFitnessGoals() {
        AccountManagement.ClientProfile profile = new AccountManagement.ClientProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "", "Vegetarian");
        assertEquals("Not specified", profile.getFitnessGoals());
    }

    @Test
    public void testClientProfileConstructorEmptyDietaryPreferences() {
        AccountManagement.ClientProfile profile = new AccountManagement.ClientProfile("Amr Jamhour", "amr.jamhour@example.com", 30, "Weight loss", "");
        assertEquals("Not specified", profile.getDietaryPreferences());
    }
}