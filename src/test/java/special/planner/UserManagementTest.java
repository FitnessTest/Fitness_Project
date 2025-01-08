package special.planner;

import com.example.UserManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagementTest {

    private UserManagement userManagement;

    @BeforeEach
    public void setUp() {
        userManagement = new UserManagement(new ArrayList<>());
    }

    @Test
    public void testAddUser() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        assertEquals(1, ((List<UserManagement.User>) userManagement.getUsers()).size());
    }

    @Test
    public void testAddUserDuplicateId() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.addUser("1", "newpassword", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        assertEquals(1, ((List<UserManagement.User>) userManagement.getUsers()).size());
    }
    @Test
    public void testUpdateUser() {

        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");

        userManagement.updateUser("1", "Amr Jamhour Updated", "new.email@email.com", "newpassword");

        List<UserManagement.User> userList = (List<UserManagement.User>) userManagement.getUsers();
        UserManagement.User updatedUser = userList.get(0);


        assertEquals("Amr Jamhour Updated", updatedUser.name);
        assertEquals("new.email@email.com", updatedUser.email);
        assertEquals("newpassword", updatedUser.password);
    }

    @Test
    public void testDeactivateUser() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.deactivateUser("1");
        UserManagement.User deactivatedUser = ((List<UserManagement.User>) userManagement.getUsers()).get(0);
        assertFalse(deactivatedUser.isActive);
    }

    @Test
    public void testApproveInstructor() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.approveInstructor("1");
        UserManagement.User approvedInstructor = ((List<UserManagement.User>) userManagement.getUsers()).get(0);
        assertTrue(approvedInstructor.isApproved);
    }

    @Test
    public void testApproveInstructorAlreadyApproved() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.approveInstructor("1");
        userManagement.approveInstructor("1");
        UserManagement.User approvedInstructor = ((List<UserManagement.User>) userManagement.getUsers()).get(0);
        assertTrue(approvedInstructor.isApproved);
    }

    @Test
    public void testViewEngagementStats() {
        // Add user and simulate activity
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.simulateActivity("1", 5);

        // Capture the user to check the engagement stats
        UserManagement.User user = ((List<UserManagement.User>) userManagement.getUsers()).get(0);

        // Ensure engagement stats were updated correctly
        assertEquals(5, user.engagementStats, "The user's engagement stats should be updated correctly.");

        // Optionally, check if the method ran without exceptions by inspecting the logs or output
        // This would depend on the actual implementation of viewEngagementStats()
        userManagement.viewEngagementStats(); // Ensure it runs without exceptions
    }
    @Test
    public void testSimulateActivity() {
        userManagement.addUser("1", "password", "Amr Jamhour", "amr.jamhour@email.com", "Instructor");
        userManagement.simulateActivity("1", 5);
        UserManagement.User user = ((List<UserManagement.User>) userManagement.getUsers()).get(0);
        assertEquals(5, user.engagementStats);
    }

    @Test
    public void testSimulateActivityUserNotFound() {
        // Simulate activity for a nonexistent user
        userManagement.simulateActivity("nonexistentId", 5);

        // Verify that the appropriate message was logged
        assertTrue(userManagement.getLogs().contains("Activity simulation failed: ID not found. ID: nonexistentId"));
    }
}