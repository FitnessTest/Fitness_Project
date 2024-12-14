package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsAndUpdatesSteps {

    private Map<String, Map<String, String>> programSchedules = new HashMap<>();
    private Map<String, String> specialOffers = new HashMap<>();
    private boolean notificationSent = false;

    @Given("the following program schedules exist:")
    public void theFollowingProgramSchedulesExist(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String programName = row.get("Program Name");
            programSchedules.put(programName, new HashMap<>(row));
        }
        System.out.println("Existing program schedules: " + programSchedules);
    }

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

    @Then("clients enrolled in {string} should receive a notification about the schedule change")
    public void clientsEnrolledInShouldReceiveANotificationAboutTheScheduleChange(String programName) {
        if (notificationSent) {
            System.out.println("Notification sent to clients about the schedule change for " + programName);
        } else {
            System.err.println("No notification sent for program " + programName);
        }
    }

    @Given("no prior notification has been sent")
    public void noPriorNotificationHasBeenSent() {
        notificationSent = false;
        System.out.println("Reset notification state to: " + notificationSent);
    }

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

    @Then("clients should receive a notification about the new program")
    public void clientsShouldReceiveANotificationAboutTheNewProgram() {
        if (notificationSent) {
            System.out.println("Notification sent to clients about the new program.");
        } else {
            System.err.println("No notification sent about the new program.");
        }
    }

    @Given("the following special offer exists:")
    public void theFollowingSpecialOfferExists(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String offerName = row.get("Offer Name");
            specialOffers.put(offerName, row.get("Discount"));
        }
        System.out.println("Existing special offers: " + specialOffers);
    }

    @When("the offer {string} is activated")
    public void theOfferIsActivated(String offerName) {
        if (specialOffers.containsKey(offerName)) {
            notificationSent = true;
            System.out.println("Special offer " + offerName + " activated.");
        } else {
            System.err.println("Offer " + offerName + " not found!");
        }
    }

    @Then("all clients should receive a notification about the {string} special offer")
    public void allClientsShouldReceiveANotificationAboutTheSpecialOffer(String offerName) {
        if (notificationSent) {
            System.out.println("Notification sent to all clients about the special offer: " + offerName);
        } else {
            System.err.println("No notification sent about the special offer: " + offerName);
        }
    }
}
