package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Steps definition for the progress tracking feature.
 * This class contains the behavior for managing client progress,
 * sending reminders, and making recommendations based on the client's data.
 */
public class ProgressTrackingSteps {
    // No explicit constructor, so adding the default one with a comment.

    /**
     * Default constructor for ProgressTrackingSteps.
     * This constructor initializes the step definitions for progress tracking features.
     */
    public ProgressTrackingSteps() {
        // The constructor can initialize any necessary variables if needed.
    }
    private Map<String, ClientProgress> clients = new HashMap<>();
    private ClientProgress currentClient;
    private String recommendation;


    @Then("the attendance rate as {string}`")
    public void theAttendanceRateAs(String string) {

    }

    /**
     * Simulates gaining access to the progress tracking dashboard.
     */
    @Given("I have access to the progress tracking dashboard")
    public void iHaveAccessToTheProgressTrackingDashboard() {
        System.out.println("Access to the progress tracking dashboard granted.");
    }

    /**
     * Creates client data based on the provided information.
     * @param dataTable The table of client data to be created.
     */
    @Given("the following clients exist:")
    public void theFollowingClientsExist(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String program = row.get("Program");
            String completionRate = row.get("Completion Rate");
            String attendance = row.get("Attendance");

            clients.put(name, new ClientProgress(name, program, completionRate, attendance));
        });
    }

    /**
     * Reviews the progress of the specified client.
     * @param clientName The name of the client whose progress is being reviewed.
     */
    @When("I review the progress of {string}")
    public void iReviewTheProgressOf(String clientName) {
        currentClient = clients.get(clientName);
        Assert.assertNotNull("Client not found: " + clientName, currentClient);
    }

    /**
     * Asserts that the completion rate matches the expected value.
     * @param expectedCompletionRate The expected completion rate.
     */
    @Then("I should see the completion rate as {string}")
    public void iShouldSeeTheCompletionRateAs(String expectedCompletionRate) {
        Assert.assertEquals("Incorrect completion rate", expectedCompletionRate, currentClient.getCompletionRate());
    }

    /**
     * Asserts that the attendance rate matches the expected value.
     * @param expectedAttendanceRate The expected attendance rate.
     */
    @Then("I should see the attendance rate as {string}")
    public void iShouldSeeTheAttendanceRateAs(String expectedAttendanceRate) {
        Assert.assertEquals("Incorrect attendance rate", expectedAttendanceRate, currentClient.getAttendance());
    }

    /**
     * Simulates gaining access to the client contact system.
     */
    @Given("I have access to the client contact system")
    public void iHaveAccessToTheClientContactSystem() {
        System.out.println("Access to the client contact system granted.");
    }

    /**
     * Sends a motivational reminder to the specified client.
     * @param clientName The name of the client to whom the reminder will be sent.
     */
    @When("I send a motivational reminder to {string}")
    public void iSendAMotivationalReminderTo(String clientName) {
        currentClient = clients.get(clientName);
        Assert.assertNotNull("Client not found: " + clientName, currentClient);
        System.out.println("Motivational reminder sent to: " + clientName);
    }

    /**
     * Confirms that the reminder was sent successfully.
     */
    @Then("the reminder should be sent successfully")
    public void theReminderShouldBeSentSuccessfully() {
        System.out.println("Reminder sent successfully to " + currentClient.getName());
    }

    /**
     * Checks if the completion rate is below the specified threshold.
     * @param threshold The threshold to compare the completion rate to.
     */
    @When("the completion rate is below {string}")
    public void theCompletionRateIsBelow(String threshold) {
        int thresholdValue = Integer.parseInt(threshold.replace("%", ""));
        int completionRate = Integer.parseInt(currentClient.getCompletionRate().replace("%", ""));

        if (completionRate < thresholdValue) {
            recommendation = "Attend more group sessions";
        }
    }

    /**
     * Asserts that the recommendation matches the expected value.
     * @param expectedRecommendation The expected recommendation.
     */
    @Then("I should see a recommendation to {string}")
    public void iShouldSeeARecommendationTo(String expectedRecommendation) {
        Assert.assertEquals("Incorrect recommendation", expectedRecommendation, recommendation);
    }

    /**
     * Represents a client's progress information.
     */
    class ClientProgress {
        private final String name;
        private final String program;
        private final String completionRate;
        private final String attendance;

        public ClientProgress(String name, String program, String completionRate, String attendance) {
            this.name = name;
            this.program = program;
            this.completionRate = completionRate;
            this.attendance = attendance;
        }

        public String getName() {
            return name;
        }

        public String getProgram() {
            return program;
        }

        public String getCompletionRate() {
            return completionRate;
        }

        public String getAttendance() {
            return attendance;
        }
    }
}
