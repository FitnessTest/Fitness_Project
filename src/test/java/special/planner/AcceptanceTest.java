package special.planner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

/**
 * This class is used to execute acceptance tests for the special.planner module.
 * It uses the Cucumber framework to run feature files located in the "MyFeature" directory.
 * The test results are generated in a user-friendly HTML report format.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "MyFeature",
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"special.planner"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class AcceptanceTest {

    /**
     * Default constructor for the AcceptanceTest class.
     * This constructor is required for proper initialization of the test runner.
     */
    public AcceptanceTest() {
        // No additional initialization required
    }
}
