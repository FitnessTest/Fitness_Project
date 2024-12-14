package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagementSteps {

    private final Map<String, User> users = new HashMap<>();
    private User newUser;

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

    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAdmin() {
        System.out.println("Admin logged in successfully.");
    }

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

    @Then("the user {string} should be added successfully")
    public void theUserShouldBeAddedSuccessfully(String name) {
        assertTrue(users.containsKey(name));
        assertEquals("Active", users.get(name).status);
    }

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

    @Then("the details of {string} should be updated successfully")
    public void theDetailsOfShouldBeUpdatedSuccessfully(String name) {
        User user = users.get(name);
        assertNotNull(user);
        System.out.println("Updated details for " + name + ": Role=" + user.role + ", Email=" + user.email);
    }

    @When("I deactivate the user {string}")
    public void iDeactivateTheUser(String name) {
        User user = users.get(name);
        if (user != null) {
            user.status = "Deactivated";
        }
    }

    @Then("the status of {string} should be {string}")
    public void theStatusOfShouldBe(String name, String expectedStatus) {
        assertEquals(expectedStatus, users.get(name).status);
    }

    @Given("the following registration requests exist:")
    public void theFollowingRegistrationRequestsExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String email = row.get("Email");
            String status = row.get("Status");
            users.put(name, new User(name, "Instructor", email, status));
        });
    }

    @When("I approve the registration of {string}")
    public void iApproveTheRegistrationOf(String name) {
        User user = users.get(name);
        if (user != null && user.status.equals("Pending")) {
            user.status = "Approved";
        }
    }
}