package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

import java.util.List;

/**
 * This class contains step definitions for exploring and enrolling in programs.
 */
public class ProgramExplorationAndEnrollmentSteps {

    private ProgramExplorer programExplorer;
    private Program selectedProgram;
    /**
     * Constructor for ProgramExplorationAndEnrollmentSteps.
     * Initializes the class for use in Cucumber step definitions.
     */
    public ProgramExplorationAndEnrollmentSteps() {
        // You can initialize any necessary resources here if needed
    }
    /**
     * Initializes the program exploration page.
     */
    @Given("the user is on the program exploration page")
    public void theUserIsOnTheProgramExplorationPage() {
        programExplorer = new ProgramExplorer();
    }

    /**
     * Applies a difficulty level filter.
     *
     * @param difficulty the difficulty level to filter by
     */
    @When("the user selects {string} as the difficulty level filter")
    public void theUserSelectsDifficultyLevelFilter(String difficulty) {
        programExplorer.applyDifficultyFilter(difficulty);
    }

    /**
     * Applies a focus area filter.
     *
     * @param focusArea the focus area to filter by
     */
    @When("the user selects {string} as the focus area filter")
    public void theUserSelectsFocusAreaFilter(String focusArea) {
        programExplorer.applyFocusAreaFilter(focusArea);
    }

    /**
     * Verifies that programs match the selected difficulty level.
     *
     * @param expectedDifficulty the expected difficulty level
     */
    @Then("the user should see programs that are labeled as {string}")
    public void theUserShouldSeeProgramsLabeledAs(String expectedDifficulty) {
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
        }
    }

    /**
     * Verifies that programs focus on the selected area.
     *
     * @param expectedFocusArea the expected focus area
     */
    @Then("the user should see programs that focus on {string}")
    public void theUserShouldSeeProgramsThatFocusOn(String expectedFocusArea) {
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    /**
     * Verifies that programs match both difficulty and focus area.
     *
     * @param expectedDifficulty the expected difficulty level
     * @param expectedFocusArea  the expected focus area
     */
    @Then("the user should see programs that are {string} and focus on {string}")
    public void theUserShouldSeeProgramsThatAreAndFocusOn(String expectedDifficulty, String expectedFocusArea) {
        List<Program> filteredPrograms = programExplorer.getFilteredPrograms();
        for (Program program : filteredPrograms) {
            assertTrue(program.getDifficultyLevel().equalsIgnoreCase(expectedDifficulty));
            assertTrue(program.getFocusArea().equalsIgnoreCase(expectedFocusArea));
        }
    }

    /**
     * Selects a program with the given details.
     *
     * @param dataTable the program details in a table format
     */
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

    /**
     * Enrolls the user in the selected program.
     */
    @When("the user clicks the \"Enroll\" button")
    public void theUserClicksTheEnrollButton() {
        if (programExplorer == null) {
            programExplorer = new ProgramExplorer();
        }
        programExplorer.enrollInProgram(selectedProgram);
    }

    /**
     * Verifies that the user is enrolled in the program.
     *
     * @param expectedProgramName the name of the program
     */
    @Then("the user should be enrolled in {string} program")
    public void theUserShouldBeEnrolledInProgram(String expectedProgramName) {
        assertTrue(programExplorer.isProgramEnrolled(selectedProgram));
        assertEquals(expectedProgramName, selectedProgram.getName());
    }

    /**
     * Enrolls the user in the specified program.
     *
     * @param programName the name of the program
     */
    @Given("the user has enrolled in the {string} program")
    public void theUserHasEnrolledInTheProgram(String programName) {
        if (programExplorer == null) {
            programExplorer = new ProgramExplorer();
        }
        selectedProgram = new Program(programName, "Beginner", "Flexibility");
        programExplorer.enrollInProgram(selectedProgram);
    }

    /**
     * Displays the program schedule.
     */
    @When("the user views the program schedule")
    public void theUserViewsTheProgramSchedule() {
        programExplorer.viewProgramSchedule(selectedProgram);
    }

    /**
     * Verifies that the program's schedule is displayed.
     */
    @Then("the user should see the program's schedule with available days and times")
    public void theUserShouldSeeTheProgramsScheduleWithAvailableDaysAndTimes() {
        String schedule = programExplorer.getProgramSchedule(selectedProgram);
        assertNotNull(schedule);
        assertTrue(schedule.contains("Available Days"));
        assertTrue(schedule.contains("Available Times"));
    }

    /**
     * Represents a fitness program.
     */
    public class Program {
        private String name;
        private String difficultyLevel;
        private String focusArea;

        /**
         * Constructs a program with the specified details.
         *
         * @param name           the name of the program
         * @param difficultyLevel the difficulty level of the program
         * @param focusArea      the focus area of the program
         */
        public Program(String name, String difficultyLevel, String focusArea) {
            this.name = name;
            this.difficultyLevel = difficultyLevel;
            this.focusArea = focusArea;
        }

        /**
         * Gets the program name.
         *
         * @return the program name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the difficulty level of the program.
         *
         * @return the difficulty level
         */
        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        /**
         * Gets the focus area of the program.
         *
         * @return the focus area
         */
        public String getFocusArea() {
            return focusArea;
        }
    }

    /**
     * Handles program exploration and enrollment functionality.
     */
    public class ProgramExplorer {
        private List<Program> availablePrograms;
        private Program enrolledProgram;

        /**
         * Constructs a ProgramExplorer with predefined programs.
         */
        public ProgramExplorer() {
            availablePrograms = List.of(
                    new Program("Yoga for Flexibility", "Beginner", "Flexibility"),
                    new Program("Muscle Building Basics", "Intermediate", "Muscle Building"),
                    new Program("Advanced Strength Training", "Advanced", "Muscle Building")
            );
        }

        /**
         * Applies a filter based on difficulty level.
         *
         * @param difficulty the difficulty level to filter by
         */
        public void applyDifficultyFilter(String difficulty) {
            availablePrograms = availablePrograms.stream()
                    .filter(program -> program.getDifficultyLevel().equalsIgnoreCase(difficulty))
                    .toList();
        }

        /**
         * Applies a filter based on focus area.
         *
         * @param focusArea the focus area to filter by
         */
        public void applyFocusAreaFilter(String focusArea) {
            availablePrograms = availablePrograms.stream()
                    .filter(program -> program.getFocusArea().equalsIgnoreCase(focusArea))
                    .toList();
        }

        /**
         * Gets the list of filtered programs.
         *
         * @return the filtered programs
         */
        public List<Program> getFilteredPrograms() {
            return availablePrograms;
        }

        /**
         * Enrolls the user in the specified program.
         *
         * @param program the program to enroll in
         */
        public void enrollInProgram(Program program) {
            enrolledProgram = program;
        }

        /**
         * Checks if the user is enrolled in the specified program.
         *
         * @param program the program to check
         * @return true if the user is enrolled, false otherwise
         */
        public boolean isProgramEnrolled(Program program) {
            return enrolledProgram != null && enrolledProgram.equals(program);
        }

        /**
         * Views the schedule of the specified program.
         *
         * @param program the program to view
         */
        public void viewProgramSchedule(Program program) {
            // Placeholder method, can be expanded in future.
        }

        /**
         * Gets the schedule of the specified program.
         *
         * @param program the program to get the schedule for
         * @return the program schedule
         */
        public String getProgramSchedule(Program program) {
            return "Available Days: Monday, Wednesday\nAvailable Times: 6 PM - 8 PM";
        }
    }
}
