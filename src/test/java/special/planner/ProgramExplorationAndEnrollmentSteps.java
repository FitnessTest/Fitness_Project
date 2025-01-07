package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

import java.util.List;

public class ProgramExplorationAndEnrollmentSteps {

    private ProgramExplorer programExplorer;
    private Program selectedProgram;

    @Given("the user is on the program exploration page")
    public void theUserIsOnTheProgramExplorationPage() {

        programExplorer = new ProgramExplorer();
    }

    @When("the user selects {string} as the difficulty level filter")
    public void theUserSelectsDifficultyLevelFilter(String difficulty) {

        programExplorer.applyDifficultyFilter(difficulty);
    }

    @When("the user selects {string} as the focus area filter")
    public void theUserSelectsFocusAreaFilter(String focusArea) {

        programExplorer.applyFocusAreaFilter(focusArea);
    }

    @Then("the user should see programs that are labeled as {string}")
    public void theUserShouldSeeProgramsLabeledAs(String expectedDifficulty) {

        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
        }
    }

    @Then("the user should see programs that focus on {string}")
    public void theUserShouldSeeProgramsThatFocusOn(String expectedFocusArea) {

        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    @Then("the user should see programs that are {string} and focus on {string}")
    public void theUserShouldSeeProgramsThatAreAndFocusOn(String expectedDifficulty, String expectedFocusArea) {

        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    @Given("the user has selected a program with the following details:")
    public void theUserHasSelectedAProgramWithTheFollowingDetails(DataTable dataTable) {

        if (programExplorer == null) {
            programExplorer = new ProgramExplorer();
        }


        List<List<String>> rows = dataTable.asLists(String.class);
        String programName = rows.get(1).get(0);
        String difficulty = rows.get(1).get(1);
        String focusArea = rows.get(1).get(2);


        selectedProgram = new Program(programName, difficulty, focusArea);
    }

    @When("the user clicks the \"Enroll\" button")
    public void theUserClicksTheEnrollButton() {

        if (programExplorer == null) {
            programExplorer = new ProgramExplorer();
        }


        programExplorer.enrollInProgram(selectedProgram);
    }

    @Then("the user should be enrolled in {string} program")
    public void theUserShouldBeEnrolledInProgram(String expectedProgramName) {

        assertTrue(programExplorer.isProgramEnrolled(selectedProgram));
        assertEquals(expectedProgramName, selectedProgram.getName());
    }

    @Given("the user has enrolled in the {string} program")
    public void theUserHasEnrolledInTheProgram(String programName) {

        if (programExplorer == null) {
            programExplorer = new ProgramExplorer();
        }


        selectedProgram = new Program(programName, "Beginner", "Flexibility");
        programExplorer.enrollInProgram(selectedProgram);
    }

    @When("the user views the program schedule")
    public void theUserViewsTheProgramSchedule() {

        programExplorer.viewProgramSchedule(selectedProgram);
    }

    @Then("the user should see the program's schedule with available days and times")
    public void theUserShouldSeeTheProgramsScheduleWithAvailableDaysAndTimes() {

        String schedule = programExplorer.getProgramSchedule(selectedProgram);
        assertNotNull(schedule);
        assertTrue(schedule.contains("Available Days"));
        assertTrue(schedule.contains("Available Times"));
    }


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


    public class ProgramExplorer {
        private List<Program> availablePrograms;
        private Program enrolledProgram;

        public ProgramExplorer() {

            availablePrograms = List.of(
                    new Program("Yoga for Flexibility", "Beginner", "Flexibility"),
                    new Program("Muscle Building Basics", "Intermediate", "Muscle Building"),
                    new Program("Advanced Strength Training", "Advanced", "Muscle Building")
            );
        }

        public void applyDifficultyFilter(String difficulty) {

            availablePrograms = availablePrograms.stream()
                    .filter(program -> program.getDifficultyLevel().equalsIgnoreCase(difficulty))
                    .toList();
        }

        public void applyFocusAreaFilter(String focusArea) {

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
            // This method is a placeholder and doesn't perform any actions yet.
            // In the future, it may interact with a database or external service
            // to retrieve the program schedule based on the specific program.
            // For now, it is left empty to avoid errors in the current implementation.

            // Optionally, you can throw an exception if the method is not yet supported:
          throw new UnsupportedOperationException("Viewing program schedule is not supported yet.");
        }

        public String getProgramSchedule(Program program) {

            return "Available Days: Monday, Wednesday\nAvailable Times: 6 PM - 8 PM";
        }
    }
}
