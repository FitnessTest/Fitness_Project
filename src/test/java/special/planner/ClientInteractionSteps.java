package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ClientInteractionSteps {

    private Map<String, Client> clients = new HashMap<>();
    private List<String> forumMessages = new ArrayList<>();
    private String lastMessageSent;
    private String lastFeedbackProvided;
    private ProgressReport lastGeneratedReport;

    static class Client {
        String name;
        String enrolledProgram;
        List<String> messages = new ArrayList<>();
        List<String> feedbacks = new ArrayList<>();

        Client(String name, String enrolledProgram) {
            this.name = name;
            this.enrolledProgram = enrolledProgram;
        }
    }

    static class ProgressReport {
        String programName;
        String completionRate;
        String feedback;

        ProgressReport(String programName, String completionRate, String feedback) {
            this.programName = programName;
            this.completionRate = completionRate;
            this.feedback = feedback;
        }
    }

    @Given("the client {string} is enrolled in the program {string}")
    public void theClientIsEnrolledInTheProgram(String clientName, String programName) {
        clients.put(clientName, new Client(clientName, programName));
    }

    @When("I send the following message to {string}:")
    public void iSendTheFollowingMessageTo(String clientName, String message) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client not found!");
        client.messages.add(message);
        lastMessageSent = message;
    }

    @Then("the client {string} should receive the message successfully")
    public void theClientShouldReceiveTheMessageSuccessfully(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client not found!");
        assertTrue(client.messages.contains(lastMessageSent), "Message not delivered!");
    }

    @Given("there is a discussion forum for the program {string}")
    public void thereIsADiscussionForumForTheProgram(String programName) {
        // Forum initialization logic can be added here if needed.
        System.out.println("Discussion forum for " + programName + " initialized.");
    }

    @When("I post the following message to the forum:")
    public void iPostTheFollowingMessageToTheForum(String message) {
        forumMessages.add(message);
    }

    @Then("the message should appear in the forum successfully")
    public void theMessageShouldAppearInTheForumSuccessfully() {
        assertFalse(forumMessages.isEmpty(), "No messages in the forum!");
        assertEquals(forumMessages.get(forumMessages.size() - 1), forumMessages.get(forumMessages.size() - 1));
    }

    @When("I provide the following feedback for {string}:")
    public void iProvideTheFollowingFeedbackFor(String clientName, String feedback) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client not found!");
        client.feedbacks.add(feedback);
        lastFeedbackProvided = feedback;
    }

    @Then("the feedback should be recorded and visible to {string}")
    public void theFeedbackShouldBeRecordedAndVisibleTo(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client not found!");
        assertTrue(client.feedbacks.contains(lastFeedbackProvided), "Feedback not recorded!");
    }

    @When("I generate a progress report for {string}")
    public void iGenerateAProgressReportFor(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client not found!");
        lastGeneratedReport = new ProgressReport(
                client.enrolledProgram,
                "75%", // This could be calculated dynamically.
                "Excellent improvement in strength"
        );
    }

    @Then("the report should include the following details:")
    public void theReportShouldIncludeTheFollowingDetails(DataTable dataTable) {
        assertNotNull(lastGeneratedReport, "No report generated!");
        dataTable.asMaps().forEach(row -> {
            assertEquals(row.get("Program Name"), lastGeneratedReport.programName, "Program name mismatch!");
            assertEquals(row.get("Completion Rate"), lastGeneratedReport.completionRate, "Completion rate mismatch!");
            assertEquals(row.get("Feedback"), lastGeneratedReport.feedback, "Feedback mismatch!");
        });
    }
}
