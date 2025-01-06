package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramMonitoringSteps {

    private static final Logger logger = Logger.getLogger(ProgramMonitoringSteps.class.getName());
    private final Map<String, Program> programs = new HashMap<>();


    class Program {
        String name;
        int enrollment;
        double revenue;
        String status;

        Program(String name, int enrollment, double revenue, String status) {
            this.name = name;
            this.enrollment = enrollment;
            this.revenue = revenue;
            this.status = status;
        }
    }


    @Given("the following programs exist:")
    public void theFollowingProgramsExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            int enrollment = parseInt(row.get("Enrollment"));
            double revenue = Double.parseDouble(row.get("Revenue"));
            String status = row.get("Status");
            programs.put(name, new Program(name, enrollment, revenue, status));
        });
    }


    @When("I view the statistics for the most popular programs")
    public void iViewTheStatisticsForTheMostPopularPrograms() {
        programs.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.enrollment, p1.enrollment))
                .limit(3)
                .forEach(program -> logger.info("Program: " + program.name + ", Enrollment: " + program.enrollment));
    }


    @Then("the most popular program should be {string}")
    public void theMostPopularProgramShouldBe(String programName) {
        boolean seen = false;
        Program best = null;
        Comparator<Program> comparator = comparingInt(p -> p.enrollment);
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


    @When("I generate a report on revenue and attendance")
    public void iGenerateAReportOnRevenueAndAttendance() {
        double totalRevenue = programs.values().stream()
                .mapToDouble(program -> program.revenue)
                .sum();
        int totalEnrollment = programs.values().stream()
                .mapToInt(program -> program.enrollment)
                .sum();

        logger.info("Total Revenue: $" + totalRevenue);
        logger.info("Total Enrollment: " + totalEnrollment);
    }


    @Then("the total revenue should be {double}")
    public void theTotalRevenueShouldBe(double expectedRevenue) {
        double totalRevenue = programs.values().stream()
                .mapToDouble(program -> program.revenue)
                .sum();
        assertEquals(expectedRevenue, totalRevenue);
    }


    @Then("the total enrollment should be {int}")
    public void theTotalEnrollmentShouldBe(int expectedEnrollment) {
        int totalEnrollment = programs.values().stream()
                .mapToInt(program -> program.enrollment)
                .sum();
        assertEquals(expectedEnrollment, totalEnrollment);
    }


    @When("I track the active and completed programs")
    public void iTrackTheActiveAndCompletedPrograms() {
        long activeCount = programs.values().stream()
                .filter(program -> program.status.equals("Active"))
                .count();
        long completedCount = programs.values().stream()
                .filter(program -> program.status.equals("Completed"))
                .count();

        logger.info("Active Programs: " + activeCount);
        logger.info("Completed Programs: " + completedCount);
    }


    @Then("there should be {int} active programs and {int} completed programs")
    public void thereShouldBeActiveProgramsAndCompletedPrograms(int expectedActive, int expectedCompleted) {
        long activeCount = programs.values().stream()
                .filter(program -> program.status.equals("Active"))
                .count();
        long completedCount = programs.values().stream()
                .filter(program -> program.status.equals("Completed"))
                .count();

        assertEquals(expectedActive, activeCount);
        assertEquals(expectedCompleted, completedCount);
    }
}
