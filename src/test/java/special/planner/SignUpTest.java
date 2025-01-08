package special.planner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the sign-up functionality by verifying different email validation scenarios.
 * The scenarios cover cases like incorrect email format, email already existing, empty email,
 * and various email validation rules.
 */
public class SignUpTest {
    /**
     * Default constructor for the SignUpTest class.
     * <p>
     * This constructor does not require any specific initialization logic
     * as fields are set by each scenario in the Cucumber tests.
     * </p>
     */
    public SignUpTest() {
        // Constructor logic (if any) can go here, but for this case, it's not necessary.
    }
    private String email;
    private boolean isSignUpSuccessful;

    private static final Set<String> existingEmails = new HashSet<>();

    // Sample existing emails to simulate the case where an email already exists.
    static {
        existingEmails.add("amrojamhour4@gmail.com");
        existingEmails.add("kebab83@gmail.com");
    }

    // Regex pattern to validate the email format.
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    /**
     * Step definition for when the email format is incorrect.
     * Sets the email to an invalid format and checks if the sign-up is successful.
     */
    @When("the email format is incorrect")
    public void the_email_format_is_incorrect() {
        this.email = "invalid-email-format";  // Invalid email format
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();  // Validate email format
    }

    /**
     * Step definition for when sign-up fails due to incorrect email format.
     * Asserts that the sign-up process fails due to invalid email.
     */
    @Then("Signing up fails")
    public void signing_up_fails() {
        assertFalse("Sign-up should fail due to invalid email format", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email already exists in the system.
     * Checks if the email exists in the list of existing emails.
     *
     * @param email The email to check.
     */
    @When("The provided credentials exists, the email is {string}")
    public void the_provided_credentials_exists_the_email_is(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email);  // Sign-up fails if email exists
    }

    /**
     * Step definition for when the email is valid and not already in use.
     * Verifies that the email can be used for sign-up.
     *
     * @param email The email to check.
     */
    @When("The provided credentials exists, the email is not {string}")
    public void the_provided_credentials_exists_the_email_is_not(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();  // Email must be unique and valid
    }

    /**
     * Step definition for when sign-up is successful due to a unique and valid email.
     */
    @Then("Signing up succeeds")
    public void signing_up_succeeds() {
        assertTrue("Sign-up should succeed for a unique and valid email", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email is valid but already exists in the system.
     * Sign-up should fail in this case.
     *
     * @param email The email to check.
     */
    @When("the email is correct but already exists, the email is {string}")
    public void the_email_is_correct_but_already_exists(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();  // Sign-up should fail if email already exists
    }

    /**
     * Step definition for when sign-up fails due to an existing email.
     */
    @Then("Signing up fails due to existing email")
    public void signing_up_fails_due_to_existing_email() {
        assertFalse("Sign-up should fail because the email already exists", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email is valid and does not exist in the system.
     * Sign-up should succeed in this case.
     *
     * @param email The email to check.
     */
    @When("the email is correct and does not exist, the email is {string}")
    public void the_email_is_correct_and_does_not_exist(String email) {
        this.email = email;
        isSignUpSuccessful = !existingEmails.contains(email) && EMAIL_PATTERN.matcher(email).matches();  // Sign-up should succeed for a new email
    }

    /**
     * Step definition for when sign-up is successful with a new correct email.
     */
    @Then("Signing up should succeed with new correct email")
    public void signing_up_should_succeed_with_new_correct_email() {
        assertTrue("Sign-up should succeed with a new, valid email", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email is empty.
     * Sign-up should fail in this case as the email is required.
     */
    @When("the email is empty")
    public void the_email_is_empty() {
        this.email = "";  // Empty email
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();  // Sign-up should fail for empty email
    }

    /**
     * Step definition for when sign-up fails due to an empty email.
     */
    @Then("Signing up fails due to empty email")
    public void signing_up_fails_due_to_empty_email() {
        assertFalse("Sign-up should fail because the email is empty", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email has an invalid domain.
     * Sign-up should fail if the domain is invalid.
     *
     * @param email The email to check.
     */
    @When("the email has an invalid domain, the email is {string}")
    public void the_email_has_an_invalid_domain(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();  // Validate domain of the email
    }

    /**
     * Step definition for when sign-up fails due to an invalid domain in the email.
     */
    @Then("Signing up fails due to invalid domain")
    public void signing_up_fails_due_to_invalid_domain() {
        assertFalse("Sign-up should fail due to an invalid domain in the email", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email is missing the '@' symbol.
     * Sign-up should fail if the email is missing '@'.
     *
     * @param email The email to check.
     */
    @When("the email is missing the '@' symbol, the email is {string}")
    public void the_email_is_missing_the_at_symbol(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();  // Sign-up fails if '@' is missing
    }

    /**
     * Step definition for when sign-up fails due to missing '@' symbol in the email.
     */
    @Then("Signing up fails due to missing '@' symbol")
    public void signing_up_fails_due_to_missing_at_symbol() {
        assertFalse("Sign-up should fail because the email is missing '@'", isSignUpSuccessful);
    }

    /**
     * Step definition for when the email has special characters in the domain.
     * Sign-up should fail if the domain contains special characters.
     *
     * @param email The email to check.
     */
    @When("the email has special characters in the domain, the email is {string}")
    public void the_email_has_special_characters_in_the_domain(String email) {
        this.email = email;
        isSignUpSuccessful = EMAIL_PATTERN.matcher(email).matches();  // Sign-up fails if domain contains special characters
    }

    /**
     * Step definition for when sign-up fails due to special characters in the domain of the email.
     */
    @Then("Signing up fails due to special characters in the domain")
    public void signing_up_fails_due_to_special_characters_in_the_domain() {
        assertFalse("Sign-up should fail because the domain contains special characters", isSignUpSuccessful);
    }
}
