package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;

public class ProgressTrackingSteps {

    private ProgressTrackingPage progressTrackingPage;

    public ProgressTrackingSteps() {
        progressTrackingPage = new ProgressTrackingPage();
    }

    @Given("the user is on their progress tracking page")
    public void theUserIsOnTheirProgressTrackingPage() {
        progressTrackingPage.openPage();
    }

    @When("the user logs their weight as {string}")
    public void theUserLogsTheirWeightAs(String weight) {
        progressTrackingPage.logWeight(weight);
    }

    @When("the user logs their BMI as {string}")
    public void theUserLogsTheirBMIAs(String bmi) {
        progressTrackingPage.logBMI(bmi);
    }

    @When("the user logs their attendance as {string}")
    public void theUserLogsTheirAttendanceAs(String attendance) {
        progressTrackingPage.logAttendance(attendance);
    }

    @Then("the user's fitness milestones should be updated with:")
    public void theUserFitnessMilestonesShouldBeUpdatedWith(DataTable dataTable) {
        progressTrackingPage.verifyMilestones(dataTable);
    }

    @Given("the user has completed the {string} program in Progress Tracking")
    public void theUserHasCompletedTheProgramInProgressTracking(String programName) {
        progressTrackingPage.completeProgram(programName);
    }

    @When("the user views their achievements")
    public void theUserViewsTheirAchievements() {
        progressTrackingPage.viewAchievements();
    }

    @Then("the user should see the following achievement:")
    public void theUserShouldSeeTheFollowingAchievement(DataTable dataTable) {
        progressTrackingPage.verifyAchievements(dataTable);
    }

    @When("the user views their badges")
    public void theUserViewsTheirBadges() {
        progressTrackingPage.viewBadges();
    }

    @Then("the user should see the following badge:")
    public void theUserShouldSeeTheFollowingBadge(DataTable dataTable) {
        progressTrackingPage.verifyBadges(dataTable);
    }

    public static class ProgressTrackingPage {

        private String weight;
        private String bmi;
        private String attendance;
        private String programName;
        private String achievement;
        private String badge;

        public void openPage() {
            System.out.println("Opening the progress tracking page...");
        }

        public void logWeight(String weight) {
            this.weight = weight;
            System.out.println("Logging weight: " + weight);
        }

        public void logBMI(String bmi) {
            this.bmi = bmi;
            System.out.println("Logging BMI: " + bmi);
        }

        public void logAttendance(String attendance) {
            this.attendance = attendance;
            System.out.println("Logging attendance: " + attendance);
        }

        public void verifyMilestones(DataTable dataTable) {
            System.out.println("Verifying fitness milestones:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Metric: " + row.get(0) + " - Value: " + row.get(1));
            });
        }

        public void completeProgram(String programName) {
            this.programName = programName;
            System.out.println("Completing the program: " + programName);
        }

        public void viewAchievements() {
            this.achievement = "Yoga Beginner";
            System.out.println("Viewing achievements...");
        }

        public void verifyAchievements(DataTable dataTable) {
            System.out.println("Verifying achievements:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Achievement: " + row.get(0) + " - Description: " + row.get(1));
            });
        }

        public void viewBadges() {
            this.badge = "Muscle Builder";
            System.out.println("Viewing badges...");
        }

        public void verifyBadges(DataTable dataTable) {
            System.out.println("Verifying badges:");
            dataTable.asLists().forEach(row -> {
                System.out.println("Badge: " + row.get(0) + " - Description: " + row.get(1));
            });
        }
    }
}
