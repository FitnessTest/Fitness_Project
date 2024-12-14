package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

import java.util.List;

public class FeedbackAndReviewsSteps {

    private Program program;
    private FeedbackAndReviews feedbackAndReviews;

    @Given("the user has completed the {string} program")
    public void theUserHasCompletedTheProgram(String programName) {

        program = new Program(programName);
        feedbackAndReviews = new FeedbackAndReviews(program);
    }

    @When("the user rates the program with a score of {string}")
    public void theUserRatesTheProgramWithAScoreOf(String rating) {

        feedbackAndReviews.addRating(rating);
    }

    @When("the user writes a review saying {string}")
    public void theUserWritesAReviewSaying(String reviewText) {

        feedbackAndReviews.addReview(reviewText);
    }

    @Then("the program should have a review with:")
    public void theProgramShouldHaveAReviewWith(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        String expectedRating = rows.get(1).get(0);
        String expectedReview = rows.get(1).get(1);


        assertEquals(expectedRating, feedbackAndReviews.getRating());
        assertEquals(expectedReview, feedbackAndReviews.getReview());
    }

    @When("the user submits a suggestion saying {string}")
    public void theUserSubmitsASuggestionSaying(String suggestionText) {

        feedbackAndReviews.submitSuggestion(suggestionText);
    }

    @Then("the instructor should receive the suggestion with the following content:")
    public void theInstructorShouldReceiveTheSuggestionWithTheFollowingContent(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        String expectedSuggestion = rows.get(1).get(0);


        assertEquals(expectedSuggestion, feedbackAndReviews.getSuggestion());
    }


    public class Program {
        private String name;
        private String rating;
        private String review;
        private String suggestion;


        public Program(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        public String getRating() {
            return rating;
        }

        public String getReview() {
            return review;
        }

        public String getSuggestion() {
            return suggestion;
        }
    }

    public class FeedbackAndReviews {
        private Program program;

        public FeedbackAndReviews(Program program) {
            this.program = program;
        }

        public void addRating(String rating) {
            program.setRating(rating);
        }

        public void addReview(String review) {
            program.setReview(review);
        }

        public void submitSuggestion(String suggestion) {
            program.setSuggestion(suggestion);
        }

        public String getRating() {
            return program.getRating();
        }

        public String getReview() {
            return program.getReview();
        }

        public String getSuggestion() {
            return program.getSuggestion();
        }
    }
}
