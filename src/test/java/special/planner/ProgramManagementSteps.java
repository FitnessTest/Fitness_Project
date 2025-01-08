package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for managing fitness programs, used in Cucumber tests.
 */
public class ProgramManagementSteps {

    // A map to hold fitness programs by their title
    private Map<String, FitnessProgram> programs = new HashMap<>();
    /**
     * Default constructor for the ProgramManagementSteps class.
     * Initializes any resources required for program management steps.
     */
    public ProgramManagementSteps() {
        // Initialize resources or perform setup tasks here, if needed.
    }
    /**
     * Class representing a fitness program with its details.
     */
    class FitnessProgram {
        String title;
        String duration;
        String difficulty;
        String goals;
        double price;

        /**
         * Constructor to initialize a new fitness program.
         *
         * @param title The program title
         * @param duration The duration of the program
         * @param difficulty The difficulty level of the program
         * @param goals The goals associated with the program
         * @param price The price of the program
         */
        FitnessProgram(String title, String duration, String difficulty, String goals, double price) {
            this.title = title;
            this.duration = duration;
            this.difficulty = difficulty;
            this.goals = goals;
            this.price = price;
        }
    }

    /**
     * Given step for logging in as an instructor.
     * Prints a confirmation message indicating the instructor is logged in.
     */
    @Given("I am logged in as an instructor")
    public void iAmLoggedInAsAnInstructor() {
        System.out.println("Instructor logged in successfully.");
    }

    /**
     * When step for creating a new fitness program with given details.
     *
     * @param dataTable DataTable containing program details (Title, Duration, Difficulty, Goals, Price)
     */
    @When("I create a new program with the following details:")
    public void iCreateANewProgramWithTheFollowingDetails(DataTable dataTable) {
        // Iterate through the DataTable to create a new program for each row
        dataTable.asMaps().forEach(row -> {
            String title = row.get("Title");
            String duration = row.get("Duration");
            String difficulty = row.get("Difficulty");
            String goals = row.get("Goals");
            double price = Double.parseDouble(row.get("Price").replace("$", "")); // Parse price
            programs.put(title, new FitnessProgram(title, duration, difficulty, goals, price));
        });
    }

    /**
     * Then step to verify that the program with the specified title has been created successfully.
     *
     * @param title The title of the program to check for
     */
    @Then("the program {string} should be created successfully")
    public void theProgramShouldBeCreatedSuccessfully(String title) {
        assertTrue(programs.containsKey(title), "Program creation failed for: " + title);
    }

    /**
     * When step for updating the details of an existing program.
     *
     * @param title The title of the program to update
     * @param dataTable DataTable containing the updated program details (Duration, Difficulty, Goals, Price)
     */
    @When("I update the program details of {string} to the following:")
    public void iUpdateTheProgramDetailsOf(String title, DataTable dataTable) {
        FitnessProgram program = programs.get(title);
        if (program != null) {
            // Iterate through the DataTable and update program fields
            dataTable.asMaps().forEach(row -> {
                program.duration = row.get("Duration");
                program.difficulty = row.get("Difficulty");
                program.goals = row.get("Goals");
                program.price = Double.parseDouble(row.get("Price").replace("$", "")); // Parse price
            });
        }
    }

    /**
     * Then step to verify that the details of the program with the specified title have been updated successfully.
     *
     * @param title The title of the program to check for
     */
    @Then("the details of the program {string} should be updated successfully")
    public void theDetailsOfTheProgramShouldBeUpdatedSuccessfully(String title) {
        assertTrue(programs.containsKey(title), "Program not found: " + title);
    }

    /**
     * When step for deleting the program with the specified title.
     *
     * @param title The title of the program to delete
     */
    @When("I delete the program {string}")
    public void iDeleteTheProgram(String title) {
        programs.remove(title);
    }

    /**
     * Then step to verify that the program with the specified title has been removed successfully.
     *
     * @param title The title of the program to check for removal
     */
    @Then("the program {string} should be removed successfully")
    public void theProgramShouldBeRemovedSuccessfully(String title) {
        assertFalse(programs.containsKey(title), "Program deletion failed for: " + title);
    }
}
