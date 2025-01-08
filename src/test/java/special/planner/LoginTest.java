package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginTest {

    @Given("The user is not logged in")
    public void the_user_is_not_logged_in() {

        System.out.println("The user is not logged in.");
    }

    @When("the credentials is valid email is {string} and password is {string}")
    public void theCredentialsIsValidEmailIsAndPasswordIs(String email, String password) {

        System.out.println("Valid credentials: Email = " + email + ", Password = " + password);
    }

    @Then("User logs in successfully")
    public void user_logs_in_successfully() {

        System.out.println("User logged in successfully.");
    }

    @When("The email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String email, String password) {

        System.out.println("Invalid email: Email = " + email + ", Password = " + password);
    }

    @Then("User failed in log in")
    public void userFailedInLogIn() {

        System.out.println("User failed to log in.");
    }

    @When("The password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String email, String password) {

        System.out.println("Invalid password: Email = " + email + ", Password = " + password);
    }

    @When("The credentials is invalid, email is {string} and password is {string}")
    public void theCredentialsIsInvalidEmailIsAndPasswordIs(String email, String password) {

        System.out.println("Invalid credentials: Email = " + email + ", Password = " + password);
    }

    @When("The email format is invalid email is {string} and password is {string}")
    public void the_email_format_is_invalid_email_is_and_password_is(String email, String password) {

        System.out.println("Invalid email format: Email = " + email + ", Password = " + password);
    }

    @Then("User fails to log in due to invalid email format")
    public void user_fails_to_log_in_due_to_invalid_email_format() {

        System.out.println("User failed to log in due to invalid email format.");
    }

    @When("The email or password contains SQL injection characters")
    public void the_email_or_password_contains_sql_injection_characters() {
        System.out.println("SQL injection characters detected in email or password.");
    }

    @Then("User fails to log in due to suspicious characters")
    public void user_fails_to_log_in_due_to_suspicious_characters() {

        System.out.println("User failed to log in due to suspicious characters.");
    }
}
