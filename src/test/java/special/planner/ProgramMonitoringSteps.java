package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for monitoring and managing fitness program statistics.
 * This class contains Cucumber step definitions that interact with the fitness programs.
 */
public class ProgramMonitoringSteps {
    /**
     * Default constructor for the ProgramMonitoringSteps class.
     * Initializes any resources required for monitoring fitness programs.
     */
    public ProgramMonitoringSteps() {
        // Initialize resources or perform setup tasks here, if necessary.
    }
    // Logger to log relevant information during the test execution
    private static final Logger logger = Logger.getLogger(ProgramMonitoringSteps.class.getName());

    // Map to hold program details
    private final Map<String, Program> programs = new HashMap<>();

    /**
     * Represents a fitness program with attributes like name, enrollment count, revenue, and status.
     */
    class Program {
        String name;
        int enrollment;
        double revenue;
        String status;

        /**
         * Constructor to initialize a program with its details.
         *
         * @param name Name of the program
         * @param enrollment Number of enrollments for the program
         * @param revenue Revenue generated from the program
         * @param status Current status of the program (e.g., Active or Completed)
         */
        Program(String name, int enrollment, double revenue, String status) {
            this.name = name;
            this.enrollment = enrollment;
            this.revenue = revenue;
            this.status = status;
        }
    }

    /**
     * Given step that sets up the initial programs from the provided data table.
     *
     * @param dataTable DataTable containing program data to be loaded.
     */
    @Given("the following programs exist:")
    public void theFollowingProgramsExist(DataTable dataTable) {
        // Iterate over the data table rows and initialize programs
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            int enrollment = parseInt(row.get("Enrollment"));
            double revenue = Double.parseDouble(row.get("Revenue"));
            String status = row.get("Status");
            programs.put(name, new Program(name, enrollment, revenue, status));
        });
    }

    /**
     * When step to view the statistics for the most popular programs.
     * Sorts programs by enrollment and logs the top 3 most popular ones.
     */
    @When("I view the statistics for the most popular programs")
    public void iViewTheStatisticsForTheMostPopularPrograms() {
        // Sort programs by enrollment and log the top 3
        programs.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.enrollment, p1.enrollment))
                .limit(3)
                .forEach(program -> logger.info("Program: " + program.name + ", Enrollment: " + program.enrollment));
    }

    /**
     * Then step to verify that the most popular program matches the given name.
     *
     * @param programName Expected name of the most popular program.
     */
    @Then("the most popular program should be {string}")
    public void theMostPopularProgramShouldBe(String programName) {
        boolean seen = false;
        Program best = null;
        Comparator<Program> comparator = comparingInt(p -> p.enrollment);

        // Find the most popular program by comparing enrollment numbers
        for (Program program : programs.values()) {
            if (!seen || comparator.compare(program, best) > 0) {
                seen = true;
                best = program;
            }
        }

        Program mostPopular = !seen ? null : best;
        assertNotNull(mostPopular);
        assertEquals(programName, mostPopular.name);
    }

    /**
     * When step to generate a report on the total revenue and enrollment.
     * Logs the total revenue and total enrollment for all programs.
     */
    @When("I generate a report on revenue and attendance")
    public void iGenerateAReportOnRevenueAndAttendance() {
        double totalRevenue = programs.values().stream()
                .mapToDouble(program -> program.revenue)
                .sum();
        int totalEnrollment = programs.values().stream()
                .mapToInt(program -> program.enrollment)
                .sum();

        // Log total revenue and total enrollment
        logger.info("Total Revenue: $" + totalRevenue);
        logger.info("Total Enrollment: " + totalEnrollment);
    }

    /**
     * Then step to verify that the total revenue matches the expected value.
     *
     * @param expectedRevenue The expected total revenue.
     */
    @Then("the total revenue should be {double}")
    public void theTotalRevenueShouldBe(double expectedRevenue) {
        double totalRevenue = programs.values().stream()
                .mapToDouble(program -> program.revenue)
                .sum();
        assertEquals(expectedRevenue, totalRevenue);
    }

    /**
     * Then step to verify that the total enrollment matches the expected value.
     *
     * @param expectedEnrollment The expected total enrollment.
     */
    @Then("the total enrollment should be {int}")
    public void theTotalEnrollmentShouldBe(int expectedEnrollment) {
        int totalEnrollment = programs.values().stream()
                .mapToInt(program -> program.enrollment)
                .sum();
        assertEquals(expectedEnrollment, totalEnrollment);
    }

    /**
     * When step to track the status of active and completed programs.
     * Logs the count of active and completed programs.
     */
    @When("I track the active and completed programs")
    public void iTrackTheActiveAndCompletedPrograms() {
        long activeCount = programs.values().stream()
                .filter(program -> program.status.equals("Active"))
                .count();
        long completedCount = programs.values().stream()
                .filter(program -> program.status.equals("Completed"))
                .count();

        // Log the counts of active and completed programs
        logger.info("Active Programs: " + activeCount);
        logger.info("Completed Programs: " + completedCount);
    }

    /**
     * Then step to verify the expected number of active and completed programs.
     *
     * @param expectedActive Expected number of active programs.
     * @param expectedCompleted Expected number of completed programs.
     */
    @Then("there should be {int} active programs and {int} completed programs")
    public void thereShouldBeActiveProgramsAndCompletedPrograms(int expectedActive, int expectedCompleted) {
        long activeCount = programs.values().stream()
                .filter(program -> program.status.equals("Active"))
                .count();
        long completedCount = programs.values().stream()
                .filter(program -> program.status.equals("Completed"))
                .count();

        // Verify the expected counts for active and completed programs
        assertEquals(expectedActive, activeCount);
        assertEquals(expectedCompleted, completedCount);
    }
}
