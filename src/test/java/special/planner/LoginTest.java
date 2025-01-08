package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

/**
 * This class contains step definitions for login scenarios in the Cucumber-based testing.
 * It simulates different user login attempts including valid and invalid credentials.
 */
public class LoginTest {
    /**
     * Default constructor for the LoginTest class.
     * Initializes any required objects or states for the class.
     */
    public LoginTest() {

    }
    /**
     * Simulates the scenario where the user is not logged in.
     */
    @Given("The user is not logged in")
    public void the_user_is_not_logged_in() {
        System.out.println("The user is not logged in.");
    }

    /**
     * Simulates the scenario of providing valid credentials.
     *
     * @param email The email address provided by the user.
     * @param password The password provided by the user.
     */
    @When("the credentials is valid email is {string} and password is {string}")
    public void theCredentialsIsValidEmailIsAndPasswordIs(String email, String password) {
        System.out.println("Valid credentials: Email = " + email + ", Password = " + password);
    }

    /**
     * Simulates the successful login when valid credentials are provided.
     */
    @Then("User logs in successfully")
    public void user_logs_in_successfully() {
        System.out.println("User logged in successfully.");
    }

    /**
     * Simulates the scenario of an invalid email being provided.
     *
     * @param email The invalid email provided by the user.
     * @param password The password provided by the user.
     */
    @When("The email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String email, String password) {
        System.out.println("Invalid email: Email = " + email + ", Password = " + password);
    }

    /**
     * Simulates the scenario where the user fails to log in due to invalid email.
     */
    @Then("User failed in log in")
    public void userFailedInLogIn() {
        System.out.println("User failed to log in.");
    }

    /**
     * Simulates the scenario of an invalid password being provided.
     *
     * @param email The email provided by the user.
     * @param password The invalid password provided by the user.
     */
    @When("The password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String email, String password) {
        System.out.println("Invalid password: Email = " + email + ", Password = " + password);
    }

    /**
     * Simulates the scenario where both email and password are invalid.
     *
     * @param email The invalid email provided by the user.
     * @param password The invalid password provided by the user.
     */
    @When("The credentials is invalid, email is {string} and password is {string}")
    public void theCredentialsIsInvalidEmailIsAndPasswordIs(String email, String password) {
        System.out.println("Invalid credentials: Email = " + email + ", Password = " + password);
    }

    /**
     * Simulates the scenario where the email format is invalid.
     *
     * @param email The invalid email provided by the user.
     * @param password The password provided by the user.
     */
    @When("The email format is invalid email is {string} and password is {string}")
    public void the_email_format_is_invalid_email_is_and_password_is(String email, String password) {
        System.out.println("Invalid email format: Email = " + email + ", Password = " + password);
    }

    /**
     * Simulates the scenario where the user fails to log in due to an invalid email format.
     */
    @Then("User fails to log in due to invalid email format")
    public void user_fails_to_log_in_due_to_invalid_email_format() {
        System.out.println("User failed to log in due to invalid email format.");
    }

    /**
     * Simulates the scenario where the email or password contains SQL injection characters.
     */
    @When("The email or password contains SQL injection characters")
    public void the_email_or_password_contains_sql_injection_characters() {
        System.out.println("SQL injection characters detected in email or password.");
    }

    /**
     * Simulates the scenario where the user fails to log in due to suspicious characters (SQL injection).
     */
    @Then("User fails to log in due to suspicious characters")
    public void user_fails_to_log_in_due_to_suspicious_characters() {
        System.out.println("User failed to log in due to suspicious characters.");
    }
}
