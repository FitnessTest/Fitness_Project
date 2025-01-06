package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class ProgressTrackingSteps {

    private Map<String, ClientProgress> clients = new HashMap<>();
    private ClientProgress currentClient;
    private String recommendation;



    @Then("the attendance rate as {string}`")
    public void theAttendanceRateAs(String expectedAttendanceRate) {
        Assert.assertEquals("Incorrect attendance rate", expectedAttendanceRate, currentClient.getAttendance());
    }
    @Given("I have access to the progress tracking dashboard")
    public void iHaveAccessToTheProgressTrackingDashboard() {

        System.out.println("Access to the progress tracking dashboard granted.");
    }

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

    @When("I review the progress of {string}")
    public void iReviewTheProgressOf(String clientName) {
        currentClient = clients.get(clientName);
        Assert.assertNotNull("Client not found: " + clientName, currentClient);
    }

    @Then("I should see the completion rate as {string}")
    public void iShouldSeeTheCompletionRateAs(String expectedCompletionRate) {
        Assert.assertEquals("Incorrect completion rate", expectedCompletionRate, currentClient.getCompletionRate());
    }

    @Then("I should see the attendance rate as {string}")
    public void iShouldSeeTheAttendanceRateAs(String expectedAttendanceRate) {
        Assert.assertEquals("Incorrect attendance rate", expectedAttendanceRate, currentClient.getAttendance());
    }

    @Given("I have access to the client contact system")
    public void iHaveAccessToTheClientContactSystem() {

        System.out.println("Access to the client contact system granted.");
    }

    @When("I send a motivational reminder to {string}")
    public void iSendAMotivationalReminderTo(String clientName) {
        currentClient = clients.get(clientName);
        Assert.assertNotNull("Client not found: " + clientName, currentClient);
        System.out.println("Motivational reminder sent to: " + clientName);
    }

    @Then("the reminder should be sent successfully")
    public void theReminderShouldBeSentSuccessfully() {

        System.out.println("Reminder sent successfully to " + currentClient.getName());
    }

    @When("the completion rate is below {string}")
    public void theCompletionRateIsBelow(String threshold) {
        int thresholdValue = Integer.parseInt(threshold.replace("%", ""));
        int completionRate = Integer.parseInt(currentClient.getCompletionRate().replace("%", ""));

        if (completionRate < thresholdValue) {
            recommendation = "Attend more group sessions";
        }
    }

    @Then("I should see a recommendation to {string}")
    public void iShouldSeeARecommendationTo(String expectedRecommendation) {
        Assert.assertEquals("Incorrect recommendation", expectedRecommendation, recommendation);
    }


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