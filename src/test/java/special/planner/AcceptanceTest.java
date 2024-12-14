package special.planner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "MyFeature",monochrome = true,snippets = SnippetType.CAMELCASE,glue = {"special.planner"},
        plugin = {"pretty", "html:target/cucumber-reports.html"})

public class AcceptanceTest {

}
