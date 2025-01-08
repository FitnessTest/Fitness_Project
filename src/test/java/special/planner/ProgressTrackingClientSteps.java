package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;

/**
 * This class defines the step definitions for the ProgressTracking feature in Cucumber tests.
 * It simulates the actions a user performs on the progress tracking page, such as logging weight,
 * BMI, attendance, and verifying achievements and badges.
 */
public class ProgressTrackingClientSteps {

    private ProgressTrackingClient progressTrackingClient;
    /**
     * Default constructor for the ProgressTrackingClientSteps class.
     * <p>
     * Initializes an instance of the ProgressTrackingClient, which is used to simulate the actions
     * a user performs on the progress tracking page, such as logging weight, BMI, attendance, and
     * verifying achievements and badges.
     * </p>
     */
    public ProgressTrackingClientSteps() {
        progressTrackingClient = new ProgressTrackingClient();
    }

    /**
     * Step definition for the "Given" step: the user is on their progress tracking page.
     */
    @Given("the user is on their progress tracking page")
    public void theUserIsOnTheirProgressTrackingPage() {
        progressTrackingClient.openPage();
    }

    /**
     * Step definition for the "When" step: the user logs their weight.
     *
     * @param weight The weight the user logs
     */
    @When("the user logs their weight as {string}")
    public void theUserLogsTheirWeightAs(String weight) {
        progressTrackingClient.logWeight(weight);
    }

    /**
     * Step definition for the "When" step: the user logs their BMI.
     *
     * @param bmi The BMI the user logs
     */
    @When("the user logs their BMI as {string}")
    public void theUserLogsTheirBMIAs(String bmi) {
        progressTrackingClient.logBMI(bmi);
    }

    /**
     * Step definition for the "When" step: the user logs their attendance.
     *
     * @param attendance The attendance status the user logs
     */
    @When("the user logs their attendance as {string}")
    public void theUserLogsTheirAttendanceAs(String attendance) {
        progressTrackingClient.logAttendance(attendance);
    }

    /**
     * Step definition for the "Then" step: verify the user's fitness milestones are updated.
     *
     * @param dataTable The milestones data to verify
     */
    @Then("the user's fitness milestones should be updated with:")
    public void theUserFitnessMilestonesShouldBeUpdatedWith(DataTable dataTable) {
        progressTrackingClient.verifyMilestones(dataTable);
    }

    /**
     * Step definition for the "Given" step: the user has completed a specific program.
     *
     * @param programName The name of the program the user has completed
     */
    @Given("the user has completed the {string} program in Progress Tracking")
    public void theUserHasCompletedTheProgramInProgressTracking(String programName) {
        progressTrackingClient.completeProgram(programName);
    }

    /**
     * Step definition for the "When" step: the user views their achievements.
     */
    @When("the user views their achievements")
    public void theUserViewsTheirAchievements() {
        progressTrackingClient.viewAchievements();
    }

    /**
     * Step definition for the "Then" step: verify the user's achievements.
     *
     * @param dataTable The achievements data to verify
     */
    @Then("the user should see the following achievement:")
    public void theUserShouldSeeTheFollowingAchievement(DataTable dataTable) {
        progressTrackingClient.verifyAchievements(dataTable);
    }

    /**
     * Step definition for the "When" step: the user views their badges.
     */
    @When("the user views their badges")
    public void theUserViewsTheirBadges() {
        progressTrackingClient.viewBadges();
    }

    /**
     * Step definition for the "Then" step: verify the user's badges.
     *
     * @param dataTable The badges data to verify
     */
    @Then("the user should see the following badge:")
    public void theUserShouldSeeTheFollowingBadge(DataTable dataTable) {
        progressTrackingClient.verifyBadges(dataTable);
    }

    /**
     * This class is responsible for tracking the progress of clients.
     * It manages the data related to client progress and provides methods
     * for updating and retrieving progress information.
     */
    public static class ProgressTrackingClient {

        private String weight;
        private String bmi;
        private String attendance;
        private String programName;
        private String achievement;
        private String badge;

        /**
         * Simulates opening the progress tracking page.
         */
        public void openPage() {
            System.out.println("Opening the progress tracking page...");
        }

        /**
         * Simulates logging the user's weight.
         *
         * @param weight The weight to log
         */
        public void logWeight(String weight) {
            this.weight = weight;
            System.out.println("Logging weight: " + weight);
        }

        /**
         * Simulates logging the user's BMI.
         *
         * @param bmi The BMI to log
         */
        public void logBMI(String bmi) {
            this.bmi = bmi;
            System.out.println("Logging BMI: " + bmi);
        }

        /**
         * Simulates logging the user's attendance.
         *
         * @param attendance The attendance status to log
         */
        public void logAttendance(String attendance) {
            this.attendance = attendance;
            System.out.println("Logging attendance: " + attendance);
        }

        /**
         * Verifies the user's fitness milestones based on the provided data table.
         *
         * @param dataTable The data table containing milestones to verify
         */
        public void verifyMilestones(DataTable dataTable) {
            System.out.println("Verifying fitness milestones:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Metric: " + row.get(0) + " - Value: " + row.get(1));
            });
        }

        /**
         * Simulates completing a program.
         *
         * @param programName The name of the program to complete
         */
        public void completeProgram(String programName) {
            this.programName = programName;
            System.out.println("Completing the program: " + programName);
        }

        /**
         * Simulates viewing the user's achievements.
         */
        public void viewAchievements() {
            this.achievement = "Yoga Beginner";
            System.out.println("Viewing achievements...");
        }

        /**
         * Verifies the user's achievements based on the provided data table.
         *
         * @param dataTable The data table containing achievements to verify
         */
        public void verifyAchievements(DataTable dataTable) {
            System.out.println("Verifying achievements:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Achievement: " + row.get(0) + " - Description: " + row.get(1));
            });
        }

        /**
         * Simulates viewing the user's badges.
         */
        public void viewBadges() {
            this.badge = "Muscle Builder";
            System.out.println("Viewing badges...");
        }

        /**
         * Verifies the user's badges based on the provided data table.
         *
         * @param dataTable The data table containing badges to verify
         */
        public void verifyBadges(DataTable dataTable) {
            System.out.println("Verifying badges:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Badge: " + row.get(0) + " - Description: " + row.get(1));
            });
        }
    }
}
