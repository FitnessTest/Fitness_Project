package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains step definitions for handling notifications and updates related to program schedules
 * and special offers in the fitness management system.
 */
public class NotificationsAndUpdatesSteps {

    private Map<String, Map<String, String>> programSchedules = new HashMap<>();
    private Map<String, String> specialOffers = new HashMap<>();
    private boolean notificationSent = false;
    /**
     * Default constructor for NotificationsAndUpdatesSteps.
     * This constructor is provided to initialize the class
     * without any specific parameters.
     */
    public NotificationsAndUpdatesSteps() {
        // No specific initialization required for now
    }
    /**
     * Given step that initializes the existing program schedules.
     * @param dataTable DataTable containing the program schedules to be added.
     */
    @Given("the following program schedules exist:")
    public void theFollowingProgramSchedulesExist(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String programName = row.get("Program Name");
            programSchedules.put(programName, new HashMap<>(row));
        }
        System.out.println("Existing program schedules: " + programSchedules);
    }

    /**
     * When step that handles the change of schedule for a specific program.
     * @param programName The name of the program whose schedule is being changed.
     * @param dataTable DataTable containing the new schedule details for the program.
     */
    @When("the schedule for {string} changes to:")
    public void theScheduleForChangesTo(String programName, DataTable dataTable) {
        if (programSchedules.containsKey(programName)) {
            Map<String, String> updatedDetails = dataTable.asMap(String.class, String.class);
            programSchedules.get(programName).putAll(updatedDetails);
            notificationSent = true;
            System.out.println("Schedule for " + programName + " updated to: " + updatedDetails);
        } else {
            System.err.println("Program " + programName + " not found!");
        }
    }

    /**
     * Then step that checks if clients enrolled in the program receive a notification about the schedule change.
     * @param programName The name of the program whose clients should receive notifications.
     */
    @Then("clients enrolled in {string} should receive a notification about the schedule change")
    public void clientsEnrolledInShouldReceiveANotificationAboutTheScheduleChange(String programName) {
        if (notificationSent) {
            System.out.println("Notification sent to clients about the schedule change for " + programName);
        } else {
            System.err.println("No notification sent for program " + programName);
        }
    }

    /**
     * Given step that resets the notification state to ensure no prior notification has been sent.
     */
    @Given("no prior notification has been sent")
    public void noPriorNotificationHasBeenSent() {
        notificationSent = false;
        System.out.println("Reset notification state to: " + notificationSent);
    }

    /**
     * When step that adds a new program with specific details.
     * @param programName The name of the new program being added.
     * @param dataTable DataTable containing the details of the new program.
     */
    @When("a new program {string} is added with the following details:")
    public void aNewProgramIsAddedWithTheFollowingDetails(String programName, DataTable dataTable) {
        List<Map<String, String>> programDetailsList = dataTable.asMaps(String.class, String.class);
        if (programDetailsList.size() == 1) {
            Map<String, String> programDetails = programDetailsList.get(0);
            programSchedules.put(programName, programDetails);
            notificationSent = true;
            System.out.println("New program " + programName + " added with details: " + programDetails);
        } else {
            System.err.println("Expected one set of program details but got multiple.");
        }
    }

    /**
     * Then step that checks if clients receive a notification about the new program.
     */
    @Then("clients should receive a notification about the new program")
    public void clientsShouldReceiveANotificationAboutTheNewProgram() {
        if (notificationSent) {
            System.out.println("Notification sent to clients about the new program.");
        } else {
            System.err.println("No notification sent about the new program.");
        }
    }

    /**
     * Given step that initializes the special offer data.
     * @param dataTable DataTable containing the special offers to be added.
     */
    @Given("the following special offer exists:")
    public void theFollowingSpecialOfferExists(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String offerName = row.get("Offer Name");
            specialOffers.put(offerName, row.get("Discount"));
        }
        System.out.println("Existing special offers: " + specialOffers);
    }

    /**
     * When step that activates a specific special offer.
     * @param offerName The name of the special offer to be activated.
     */
    @When("the offer {string} is activated")
    public void theOfferIsActivated(String offerName) {
        if (specialOffers.containsKey(offerName)) {
            notificationSent = true;
            System.out.println("Special offer " + offerName + " activated.");
        } else {
            System.err.println("Offer " + offerName + " not found!");
        }
    }

    /**
     * Then step that checks if all clients receive a notification about the special offer.
     * @param offerName The name of the special offer about which clients should be notified.
     */
    @Then("all clients should receive a notification about the {string} special offer")
    public void allClientsShouldReceiveANotificationAboutTheSpecialOffer(String offerName) {
        if (notificationSent) {
            System.out.println("Notification sent to all clients about the special offer: " + offerName);
        } else {
            System.err.println("No notification sent about the special offer: " + offerName);
        }
    }
}
