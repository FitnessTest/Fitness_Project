package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private String email;
    private String password;
    private boolean isLoggedIn;
    private static final Map<String, String> validCredentials = new HashMap<>();

    static {
        validCredentials.put("kebab83@gmail.com", "Ihab");
        validCredentials.put("amrojamhour4@gmail.com", "Amr");
    }

    @Given("The user is not logged in")
    public void the_user_is_not_logged_in() {
        isLoggedIn = false;
    }

    @When("the credentials is valid email is {string} and password is {string}")
    public void the_credentials_is_valid_email_is_and_password_is(String email, String password) {
        this.email = email;
        this.password = password;
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User logs in successfully")
    public void user_logs_in_successfully() {
        assertTrue("User should be logged in", isLoggedIn);
    }

    @When("The email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String email, String password) {
        this.email = email;
        this.password = password;
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User failed in log in")
    public void user_failed_in_log_in() {
        assertFalse("User should not be logged in", isLoggedIn);
    }

    @When("The password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String email, String password) {
        this.email = email;
        this.password = password;
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @When("The credentials is invalid, email is {string} and password is {string}")
    public void the_credentials_is_invalid_email_is_and_password_is(String email, String password) {
        this.email = email;
        this.password = password;
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @When("The email is empty")
    public void the_email_is_empty() {
        this.email = "";
        this.password = "Ihab";
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User fails to log in due to empty email")
    public void user_fails_to_log_in_due_to_empty_email() {
        assertFalse("User should not be logged in due to empty email", isLoggedIn);
    }

    @When("The password is empty")
    public void the_password_is_empty() {
        this.email = "kebab83@gmail.com"; // Example valid email
        this.password = "";
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User fails to log in due to empty password")
    public void user_fails_to_log_in_due_to_empty_password() {
        assertFalse("User should not be logged in due to empty password", isLoggedIn);
    }

    @When("The email format is invalid email is {string} and password is {string}")
    public void the_email_format_is_invalid_email_is_and_password_is(String email, String password) {
        this.email = email;
        this.password = password;
        // Assuming an email must contain "@" symbol for simplicity
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password) && email.contains("@");
    }

    @Then("User fails to log in due to invalid email format")
    public void user_fails_to_log_in_due_to_invalid_email_format() {
        assertFalse("User should not be logged in due to invalid email format", isLoggedIn);
    }

    @When("The credentials are missing, email is empty and password is empty")
    public void the_credentials_are_missing() {
        this.email = "";
        this.password = "";
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User fails to log in due to missing credentials")
    public void user_fails_to_log_in_due_to_missing_credentials() {
        assertFalse("User should not be logged in due to missing credentials", isLoggedIn);
    }

    // Additional Test Cases:

    @When("The user attempts multiple failed logins")
    public void the_user_attempts_multiple_failed_logins() {
        // Trying multiple failed attempts
        isLoggedIn = false; // Reset before starting the sequence
        the_credentials_is_invalid_email_is_and_password_is("wrongemail@example.com", "wrongpassword");
        the_credentials_is_invalid_email_is_and_password_is("wrongemail2@example.com", "wrongpassword2");
    }

    @Then("User should still not be logged in")
    public void user_should_still_not_be_logged_in() {
        assertFalse("User should not be logged in after multiple failed attempts", isLoggedIn);
    }

    @When("The user provides valid email but invalid password")
    public void the_user_provides_valid_email_but_invalid_password() {
        this.email = "kebab83@gmail.com";
        this.password = "wrongpassword";
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User fails to log in due to incorrect password")
    public void user_fails_to_log_in_due_to_incorrect_password() {
        assertFalse("User should not be logged in due to incorrect password", isLoggedIn);
    }

    @When("The email or password contains SQL injection characters")
    public void the_email_or_password_contains_sql_injection_characters() {
        this.email = "kebab83@gmail.com' OR '1'='1";
        this.password = "Ihab' OR '1'='1";
        isLoggedIn = validCredentials.containsKey(email) && validCredentials.get(email).equals(password);
    }

    @Then("User fails to log in due to suspicious characters")
    public void user_fails_to_log_in_due_to_suspicious_characters() {
        assertFalse("User should not be logged in due to SQL injection characters", isLoggedIn);
    }
}
