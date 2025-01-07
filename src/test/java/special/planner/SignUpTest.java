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
        assertFalse("Sign-up should fail", isSignUpSuccessful);
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
        assertTrue("Sign-up should succeed", isSignUpSuccessful);
    }
}