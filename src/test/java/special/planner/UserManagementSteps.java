package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for user management in the application.
 * This class implements the behavior for actions like adding users, updating details,
 * deactivating users, and approving registration requests as part of Cucumber BDD tests.
 */
public class UserManagementSteps {
    /**
     * Default constructor for the UserManagementSteps class.
     * This constructor is provided for explicit initialization of resources or class setup,
     * though no resources are initialized in this case.
     *
     * Cucumber step definition classes generally do not require explicit constructors,
     * as the framework instantiates them automatically. However, this constructor can be
     * useful if additional setup is needed.
     */
    public UserManagementSteps() {
        // Initialize any resources if needed
        // In this case, we have a default constructor, so no resources to initialize
    }
    private final Map<String, User> users = new HashMap<>();
    private User newUser;

    /**
     * Represents a User with basic attributes like name, role, email, and status.
     */
    static class User {
        String name;
        String role;
        String email;
        String status;

        User(String name, String role, String email, String status) {
            this.name = name;
            this.role = role;
            this.email = email;
            this.status = status;
        }
    }

    /**
     * This method simulates logging in as an admin.
     * It is used to initialize the test scenario where the admin has logged in.
     */
    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAdmin() {
        System.out.println("Admin logged in successfully.");
    }

    /**
     * This method simulates adding a new user with the provided details.
     * It processes the provided DataTable containing user details and adds them to the users map.
     *
     * @param dataTable The table containing the user details.
     */
    @When("I add a new user with the following details:")
    public void iAddANewUserWithTheFollowingDetails(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String role = row.get("Role");
            String email = row.get("Email");
            newUser = new User(name, role, email, "Active");
            users.put(name, newUser);
        });
    }

    /**
     * This method checks whether a user has been successfully added.
     * It asserts that the user exists in the users map and that their status is active.
     *
     * @param name The name of the user to verify.
     */
    @Then("the user {string} should be added successfully")
    public void theUserShouldBeAddedSuccessfully(String name) {
        assertTrue(users.containsKey(name));
        assertEquals("Active", users.get(name).status);
    }

    /**
     * This method initializes the test scenario by adding users that already exist.
     * It processes the provided DataTable to populate the users map.
     *
     * @param dataTable The table containing the existing user details.
     */
    @Given("the following users exist:")
    public void theFollowingUsersExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String role = row.get("Role");
            String email = row.getOrDefault("Email", "N/A");
            String status = row.get("Status");
            users.put(name, new User(name, role, email, status));
        });
    }

    /**
     * This method updates the details of an existing user with new values from a DataTable.
     *
     * @param name      The name of the user to update.
     * @param dataTable The new details to update the user with.
     */
    @When("I update the details of {string} to the following:")
    public void iUpdateTheDetailsOfToTheFollowing(String name, DataTable dataTable) {
        User user = users.get(name);
        if (user != null) {
            dataTable.asMaps().forEach(row -> {
                user.role = row.getOrDefault("Role", user.role);
                user.email = row.getOrDefault("Email", user.email);
            });
        }
    }

    /**
     * This method checks whether the details of a user have been updated successfully.
     * It prints the updated details of the user for verification.
     *
     * @param name The name of the user to verify.
     */
    @Then("the details of {string} should be updated successfully")
    public void theDetailsOfShouldBeUpdatedSuccessfully(String name) {
        User user = users.get(name);
        assertNotNull(user);
        System.out.println("Updated details for " + name + ": Role=" + user.role + ", Email=" + user.email);
    }

    /**
     * This method simulates deactivating a user.
     * It changes the status of the user to "Deactivated".
     *
     * @param name The name of the user to deactivate.
     */
    @When("I deactivate the user {string}")
    public void iDeactivateTheUser(String name) {
        User user = users.get(name);
        if (user != null) {
            user.status = "Deactivated";
        }
    }

    /**
     * This method checks whether the status of a user matches the expected status.
     *
     * @param name           The name of the user.
     * @param expectedStatus The expected status of the user.
     */
    @Then("the status of {string} should be {string}")
    public void theStatusOfShouldBe(String name, String expectedStatus) {
        assertEquals(expectedStatus, users.get(name).status);
    }

    /**
     * This method initializes the test scenario with registration requests.
     * It processes the provided DataTable to add pending registration requests to the users map.
     *
     * @param dataTable The table containing registration request details.
     */
    @Given("the following registration requests exist:")
    public void theFollowingRegistrationRequestsExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String email = row.get("Email");
            String status = row.get("Status");
            users.put(name, new User(name, "Instructor", email, status));
        });
    }

    /**
     * This method approves a registration request for a user, changing their status to "Approved".
     *
     * @param name The name of the user whose registration is to be approved.
     */
    @When("I approve the registration of {string}")
    public void iApproveTheRegistrationOf(String name) {
        User user = users.get(name);
        if (user != null && user.status.equals("Pending")) {
            user.status = "Approved";
        }
    }
}
