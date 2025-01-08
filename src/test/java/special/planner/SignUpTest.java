package special.planner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignUpTest {

    private String email;
    private boolean isSignUpSuccessful;

    private static final Set<String> existingEmails = new HashSet<>();

    static {
        existingEmails.add("amrojamhour4@gmail.com");
        existingEmails.add("kebab83@gmail.com");
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    @When("the email format is incorrect")
    public void the_email_format_is_incorrect() {
        this.email = "invalid-email-format";
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails")
    public void signing_up_fails() {
        assertFalse("Sign-up should fail due to invalid email format", isSignUpSuccessful);
    }

    @When("The provided credentials exists, the email is {string}")
    public void the_provided_credentials_exists_the_email_is(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email);
    }

    @When("The provided credentials exists, the email is not {string}")
    public void the_provided_credentials_exists_the_email_is_not(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up succeeds")
    public void signing_up_succeeds() {
        assertTrue("Sign-up should succeed for a unique and valid email", isSignUpSuccessful);
    }

    @When("the email is correct but already exists, the email is {string}")
    public void the_email_is_correct_but_already_exists(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails due to existing email")
    public void signing_up_fails_due_to_existing_email() {
        assertFalse("Sign-up should fail because the email already exists", isSignUpSuccessful);
    }

    @When("the email is correct and does not exist, the email is {string}")
    public void the_email_is_correct_and_does_not_exist(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up should succeed with new correct email")
    public void signing_up_should_succeed_with_new_correct_email() {
        assertTrue("Sign-up should succeed with a new, valid email", isSignUpSuccessful);
    }

    @When("the email is empty")
    public void the_email_is_empty() {
        this.email = "";
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails due to empty email")
    public void signing_up_fails_due_to_empty_email() {
        assertFalse("Sign-up should fail because the email is empty", isSignUpSuccessful);
    }

    @When("the email has an invalid domain, the email is {string}")
    public void the_email_has_an_invalid_domain(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails due to invalid domain")
    public void signing_up_fails_due_to_invalid_domain() {
        assertFalse("Sign-up should fail due to an invalid domain in the email", isSignUpSuccessful);
    }

    @When("the email is missing the '@' symbol, the email is {string}")
    public void the_email_is_missing_the_at_symbol(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails due to missing '@' symbol")
    public void signing_up_fails_due_to_missing_at_symbol() {
        assertFalse("Sign-up should fail because the email is missing '@'", isSignUpSuccessful);
    }

    @When("the email has special characters in the domain, the email is {string}")
    public void the_email_has_special_characters_in_the_domain(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();
    }

    @Then("Signing up fails due to special characters in the domain")
    public void signing_up_fails_due_to_special_characters_in_the_domain() {
        assertFalse("Sign-up should fail because the domain contains special characters", isSignUpSuccessful);
    }
}
