package special.planner;

import com.example.UserManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UserManagement class.
 * This class tests all functionalities of UserManagement such as adding, updating,
 * deactivating users, and simulating activity.
 */
public class UserManagementTest {
    /**
     * Default constructor for the UserManagementTest class.
     * <p>
     * This constructor initializes the UserManagement instance with an empty user list.
     * Optional custom initialization logic can be added if needed.
     */
    public UserManagementTest() {
        // Optional custom initialization logic can go here
    }
    private UserManagement userManagement;

    /**
     * Sets up the UserManagement instance before each test.
     * This method is called before each test case to initialize the user management system.
     */
    @BeforeEach
    public void setUp() {
        userManagement = new UserManagement(new ArrayList<>());
    }

    /**
     * Test case for adding a new user.
     * This test verifies that a user can be added successfully.
     */
    @Test
    public void testAddUser() {
        // Testing if a user can be added successfully
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        assertEquals(1, ((List<UserManagement.User>) userManagement.getUsers()).size());
    }

    /**
     * Test case for adding a user with a duplicate ID.
     * This test checks that a duplicate user ID does not add a new user.
     */
    @Test
    public void testAddUserDuplicateId() {
        // Testing if adding a user with a duplicate ID does not add a new user
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.addUser("1", "newpassword", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        assertEquals(1, ((List<UserManagement.User>) userManagement.getUsers()).size());
    }

    /**
     * Test case for updating user details.
     * This test verifies that a user's details can be updated correctly.
     */
    @Test
    public void testUpdateUser() {
        // Testing if a user's details can be updated correctly
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.updateUser("1", "Amr Jamhour Updated", "new.email@email.com", "newpassword");

        List<UserManagement.User> userList = (List<UserManagement.User>) userManagement.getUsers();
        UserManagement.User updatedUser = userList.get(0);

        // Verifying the updated user details
        assertEquals("Amr Jamhour Updated", updatedUser.name);
        assertEquals("new.email@email.com", updatedUser.email);
        assertEquals("newpassword", updatedUser.password);
    }

    /**
     * Test case for deactivating a user.
     * This test ensures that a user can be deactivated successfully.
     */
    @Test
    public void testDeactivateUser() {
        // Testing if a user can be deactivated successfully
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.deactivateUser("1");
        UserManagement.User deactivatedUser = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Verifying that the user's active status is set to false
        assertFalse(deactivatedUser.isActive);
    }

    /**
     * Test case for approving an instructor.
     * This test checks if an instructor can be approved successfully.
     */
    @Test
    public void testApproveInstructor() {
        // Testing if an instructor can be approved successfully
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.approveInstructor("1");
        UserManagement.User approvedInstructor = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Verifying that the instructor is approved
        assertTrue(approvedInstructor.isApproved);
    }

    /**
     * Test case for attempting to approve an already approved instructor.
     * This test checks if approving an already approved instructor does not change their status.
     */
    @Test
    public void testApproveInstructorAlreadyApproved() {
        // Testing if approving an already approved instructor does not change their status
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.approveInstructor("1");
        userManagement.approveInstructor("1");
        UserManagement.User approvedInstructor = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Verifying that the instructor's approval status remains unchanged
        assertTrue(approvedInstructor.isApproved);
    }

    /**
     * Test case for viewing engagement stats.
     * This test verifies that user engagement stats are displayed correctly.
     */
    @Test
    public void testViewEngagementStats() {
        // Testing if user engagement stats are displayed correctly
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.simulateActivity("1", 5);

        UserManagement.User user = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Verifying the user's engagement stats
        assertEquals(5, user.engagementStats, "The user's engagement stats should be updated correctly.");

        // Calling the method to ensure it runs without exceptions
        userManagement.viewEngagementStats();
    }

    /**
     * Test case for simulating activity for a user.
     * This test checks if user activity simulation works as expected.
     */
    @Test
    public void testSimulateActivity() {
        // Testing if user activity simulation works as expected
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.simulateActivity("1", 5);
        UserManagement.User user = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Verifying that the simulated activity updates the engagement stats
        assertEquals(5, user.engagementStats);
    }

    /**
     * Test case for simulating activity for a nonexistent user.
     * This test ensures that simulating activity for a nonexistent user logs the appropriate error message.
     */
    @Test
    public void testSimulateActivityUserNotFound() {
        // Testing if simulating activity for a nonexistent user logs the appropriate error message
        userManagement.simulateActivity("nonexistentId", 5);

        // Verifying that the appropriate error message is logged
        assertTrue(userManagement.getLogs().contains("Activity simulation failed: ID not found. ID: nonexistentId"));
    }
}
