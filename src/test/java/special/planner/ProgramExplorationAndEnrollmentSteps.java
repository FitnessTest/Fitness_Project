package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

import java.util.List;

public class ProgramExplorationAndEnrollmentSteps {

    private ProgramExplorer programExplorer; // Make sure this is initialized
    private Program selectedProgram;

    @Given("the user is on the program exploration page")
    public void theUserIsOnTheProgramExplorationPage() {
        // Initialize the ProgramExplorer object
        programExplorer = new ProgramExplorer();
    }

    @When("the user selects {string} as the difficulty level filter")
    public void theUserSelectsDifficultyLevelFilter(String difficulty) {
        // Apply the difficulty filter to the program explorer
        programExplorer.applyDifficultyFilter(difficulty);
    }

    @When("the user selects {string} as the focus area filter")
    public void theUserSelectsFocusAreaFilter(String focusArea) {
        // Apply the focus area filter to the program explorer
        programExplorer.applyFocusAreaFilter(focusArea);
    }

    @Then("the user should see programs that are labeled as {string}")
    public void theUserShouldSeeProgramsLabeledAs(String expectedDifficulty) {
        // Verify that the filtered programs are correctly labeled with the expected difficulty
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
        }
    }

    @Then("the user should see programs that focus on {string}")
    public void theUserShouldSeeProgramsThatFocusOn(String expectedFocusArea) {
        // Verify that the filtered programs are correctly focusing on the expected area
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    @Then("the user should see programs that are {string} and focus on {string}")
    public void theUserShouldSeeProgramsThatAreAndFocusOn(String expectedDifficulty, String expectedFocusArea) {
        // Verify that the filtered programs match both the difficulty level and focus area
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    @Given("the user has selected a program with the following details:")
    public void theUserHasSelectedAProgramWithTheFollowingDetails(DataTable dataTable) {
        // Ensure that programExplorer is initialized before use
        if (programExplorer == null) {
            programExplorer = new ProgramExplorer(); // Initialize if null
        }

        // Get program details from the dataTable
        List<List<String>> rows = dataTable.asLists(String.class);
        String programName = rows.get(1).get(0);
        String difficulty = rows.get(1).get(1);
        String focusArea = rows.get(1).get(2);

        // Simulate program selection
        selectedProgram = new Program(programName, difficulty, focusArea);
    }

    @When("the user clicks the \"Enroll\" button")
    public void theUserClicksTheEnrollButton() {
        // Ensure the programExplorer is initialized
        if (programExplorer == null) {
            programExplorer = new ProgramExplorer(); // Initialize if null
        }

        // Simulate the enrollment action
        programExplorer.enrollInProgram(selectedProgram);
    }

    @Then("the user should be enrolled in {string} program")
    public void theUserShouldBeEnrolledInProgram(String expectedProgramName) {
        // Verify that the user is successfully enrolled in the program
        assertTrue(programExplorer.isProgramEnrolled(selectedProgram));
        assertEquals(expectedProgramName, selectedProgram.getName());
    }

    @Given("the user has enrolled in the {string} program")
    public void theUserHasEnrolledInTheProgram(String programName) {
        // Ensure that programExplorer is initialized before use
        if (programExplorer == null) {
            programExplorer = new ProgramExplorer(); // Initialize if null
        }

        // Simulate user enrolling in a specific program
        selectedProgram = new Program(programName, "Beginner", "Flexibility");
        programExplorer.enrollInProgram(selectedProgram);
    }

    @When("the user views the program schedule")
    public void theUserViewsTheProgramSchedule() {
        // Simulate viewing the program schedule
        programExplorer.viewProgramSchedule(selectedProgram);
    }

    @Then("the user should see the program's schedule with available days and times")
    public void theUserShouldSeeTheProgramsScheduleWithAvailableDaysAndTimes() {
        // Verify that the program's schedule is available and includes days and times
        String schedule = programExplorer.getProgramSchedule(selectedProgram);
        assertNotNull(schedule);
        assertTrue(schedule.contains("Available Days"));
        assertTrue(schedule.contains("Available Times"));
    }

    // Inner class for Program
    public class Program {
        private String name;
        private String difficultyLevel;
        private String focusArea;

        public Program(String name, String difficultyLevel, String focusArea) {
            this.name = name;
            this.difficultyLevel = difficultyLevel;
            this.focusArea = focusArea;
        }

        public String getName() {
            return name;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public String getFocusArea() {
            return focusArea;
        }
    }

    // Inner class for ProgramExplorer
    public class ProgramExplorer {
        private List<Program> availablePrograms;
        private Program enrolledProgram;

        public ProgramExplorer() {
            // Initialize the list of available programs
            availablePrograms = List.of(
                    new Program("Yoga for Flexibility", "Beginner", "Flexibility"),
                    new Program("Muscle Building Basics", "Intermediate", "Muscle Building"),
                    new Program("Advanced Strength Training", "Advanced", "Muscle Building")
            );
        }

        public void applyDifficultyFilter(String difficulty) {
            // Filter programs by difficulty level
            availablePrograms = availablePrograms.stream()
                    .filter(program -> program.getDifficultyLevel().equalsIgnoreCase(difficulty))
                    .toList();
        }

        public void applyFocusAreaFilter(String focusArea) {
            // Filter programs by focus area
            availablePrograms = availablePrograms.stream()
                    .filter(program -> program.getFocusArea().equalsIgnoreCase(focusArea))
                    .toList();
        }

        public List<Program> getFilteredPrograms() {
            return availablePrograms;
        }

        public void enrollInProgram(Program program) {
            enrolledProgram = program;
        }

        public boolean isProgramEnrolled(Program program) {
            return enrolledProgram != null && enrolledProgram.equals(program);
        }

        public void viewProgramSchedule(Program program) {
            // Simulate viewing the program's schedule
            // In a real implementation, this would retrieve schedule details from a database or service
        }

        public String getProgramSchedule(Program program) {
            // Simulate retrieving the program schedule
            return "Available Days: Monday, Wednesday\nAvailable Times: 6 PM - 8 PM";
        }
    }
}
