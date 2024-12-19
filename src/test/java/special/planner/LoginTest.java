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
}
