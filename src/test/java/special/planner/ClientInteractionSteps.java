package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements step definitions for the client interaction scenarios in a Cucumber test suite.
 * It simulates operations such as sending messages, providing feedback, and generating progress reports.
 */
public class ClientInteractionSteps {

    private Map<String, Client> clients = new HashMap<>();
    private List<String> forumMessages = new ArrayList<>();
    private String lastMessageSent;
    private String lastFeedbackProvided;
    private ProgressReport lastGeneratedReport;

    /**
     * Constructs a ClientInteractionSteps object to manage client interactions
     * such as sending messages, providing feedback, and generating progress reports.
     */
    public ClientInteractionSteps() {
        // Default constructor
    }
    /**
     * Represents a client with a name, enrolled program, and lists for messages and feedback.
     */
    static class Client {
        String name;
        String enrolledProgram;
        List<String> messages = new ArrayList<>();
        List<String> feedbacks = new ArrayList<>();

        /**
         * Constructs a Client object with the specified name and enrolled program.
         *
         * @param name            the name of the client
         * @param enrolledProgram the program the client is enrolled in
         */
        Client(String name, String enrolledProgram) {
            this.name = name;
            this.enrolledProgram = enrolledProgram;
        }
    }

    /**
     * Represents a progress report with details about the program, completion rate, and feedback.
     */
    static class ProgressReport {
        String programName;
        String completionRate;
        String feedback;

        /**
         * Constructs a ProgressReport object with the given details.
         *
         * @param programName     the name of the program
         * @param completionRate  the completion rate of the program
         * @param feedback        feedback for the progress
         */
        ProgressReport(String programName, String completionRate, String feedback) {
            this.programName = programName;
            this.completionRate = completionRate;
            this.feedback = feedback;
        }
    }

    /**
     * Adds a client and enrolls them in a program.
     *
     * @param clientName the name of the client
     * @param programName the name of the program
     */
    @Given("the client {string} is enrolled in the program {string}")
    public void theClientIsEnrolledInTheProgram(String clientName, String programName) {
        if (programName.isEmpty()) {
            throw new IllegalArgumentException("Program name cannot be empty!");
        }
        clients.put(clientName, new Client(clientName, programName));
    }

    /**
     * Sends a message to the specified client.
     *
     * @param clientName the name of the client
     * @param message the message to be sent
     */
    @When("I send the following message to {string}:")
    public void iSendTheFollowingMessageTo(String clientName, String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty!");
        }
        Client client = clients.get(clientName);
        assertNotNull(client, "Client " + clientName + " not found!");
        client.messages.add(message);
        lastMessageSent = message;
    }

    /**
     * Verifies that the specified client received the message.
     *
     * @param clientName the name of the client
     */
    @Then("the client {string} should receive the message successfully")
    public void theClientShouldReceiveTheMessageSuccessfully(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client " + clientName + " not found!");
        assertTrue(client.messages.contains(lastMessageSent), "Message not delivered!");
    }

    /**
     * Initializes a discussion forum for a program.
     *
     * @param programName the name of the program
     */
    @Given("there is a discussion forum for the program {string}")
    public void thereIsADiscussionForumForTheProgram(String programName) {
        System.out.println("Discussion forum for " + programName + " initialized.");
    }

    /**
     * Posts a message to the discussion forum.
     *
     * @param message the message to be posted
     */
    @When("I post the following message to the forum:")
    public void iPostTheFollowingMessageToTheForum(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty!");
        }
        forumMessages.add(message);
        lastMessageSent = message;
    }

    /**
     * Verifies that the last posted message appears in the discussion forum.
     */
    @Then("the message should appear in the forum successfully")
    public void theMessageShouldAppearInTheForumSuccessfully() {
        assertFalse(forumMessages.isEmpty(), "No messages in the forum!");
        assertEquals(lastMessageSent, forumMessages.get(forumMessages.size() - 1), "The last message in the forum does not match the posted message!");
    }

    /**
     * Records feedback for a client.
     *
     * @param clientName the name of the client
     * @param feedback the feedback to be provided
     */
    @When("I provide the following feedback for {string}:")
    public void iProvideTheFollowingFeedbackFor(String clientName, String feedback) {
        if (feedback.isEmpty()) {
            throw new IllegalArgumentException("Feedback cannot be empty!");
        }
        Client client = clients.get(clientName);
        assertNotNull(client, "Client " + clientName + " not found!");
        client.feedbacks.add(feedback);
        lastFeedbackProvided = feedback;
    }

    /**
     * Verifies that feedback is recorded and visible to the client.
     *
     * @param clientName the name of the client
     */
    @Then("the feedback should be recorded and visible to {string}")
    public void theFeedbackShouldBeRecordedAndVisibleTo(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client " + clientName + " not found!");
        assertTrue(client.feedbacks.contains(lastFeedbackProvided), "Feedback not recorded!");
    }

    /**
     * Generates a progress report for a client.
     *
     * @param clientName the name of the client
     */
    @When("I generate a progress report for {string}")
    public void iGenerateAProgressReportFor(String clientName) {
        Client client = clients.get(clientName);
        assertNotNull(client, "Client " + clientName + " not found!");
        lastGeneratedReport = new ProgressReport(client.enrolledProgram, "75%", "Excellent improvement in strength");
    }

    /**
     * Verifies that the generated progress report contains the expected details.
     *
     * @param dataTable a Cucumber DataTable containing the expected report details
     */
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
