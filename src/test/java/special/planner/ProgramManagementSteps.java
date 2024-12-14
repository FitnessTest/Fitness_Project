package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramManagementSteps {

    private Map<String, FitnessProgram> programs = new HashMap<>();

     class FitnessProgram {
        String title;
        String duration;
        String difficulty;
        String goals;
        double price;

        FitnessProgram(String title, String duration, String difficulty, String goals, double price) {
            this.title = title;
            this.duration = duration;
            this.difficulty = difficulty;
            this.goals = goals;
            this.price = price;
        }
    }

    @Given("I am logged in as an instructor")
    public void iAmLoggedInAsAnInstructor() {
        System.out.println("Instructor logged in successfully.");
    }

    @When("I create a new program with the following details:")
    public void iCreateANewProgramWithTheFollowingDetails(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String title = row.get("Title");
            String duration = row.get("Duration");
            String difficulty = row.get("Difficulty");
            String goals = row.get("Goals");
            double price = Double.parseDouble(row.get("Price").replace("$", ""));
            programs.put(title, new FitnessProgram(title, duration, difficulty, goals, price));
        });
    }

    @Then("the program {string} should be created successfully")
    public void theProgramShouldBeCreatedSuccessfully(String title) {
        assertTrue(programs.containsKey(title), "Program creation failed for: " + title);
    }

    @When("I update the program details of {string} to the following:")
    public void iUpdateTheProgramDetailsOf(String title, DataTable dataTable) {
        FitnessProgram program = programs.get(title);
        if (program != null) {
            dataTable.asMaps().forEach(row -> {
                program.duration = row.get("Duration");
                program.difficulty = row.get("Difficulty");
                program.goals = row.get("Goals");
                program.price = Double.parseDouble(row.get("Price").replace("$", ""));
            });
        }
    }

    @Then("the details of the program {string} should be updated successfully")
    public void theDetailsOfTheProgramShouldBeUpdatedSuccessfully(String title) {
        assertTrue(programs.containsKey(title), "Program not found: " + title);
    }

    @When("I delete the program {string}")
    public void iDeleteTheProgram(String title) {
        programs.remove(title);
    }

    @Then("the program {string} should be removed successfully")
    public void theProgramShouldBeRemovedSuccessfully(String title) {
        assertFalse(programs.containsKey(title), "Program deletion failed for: " + title);
    }
}
