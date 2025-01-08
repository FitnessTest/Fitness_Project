package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Step Definitions for Feedback and Reviews related actions.
 * These steps handle the process of submitting ratings, reviews, and improvement suggestions
 * for fitness programs in the system. The class maps feature file steps to their corresponding
 * methods in this class using Cucumber annotations.
 */
public class FeedbackAndReviewsSteps {

    private Program program;
    private FeedbackAndReviews feedbackAndReviews;
    /**
     * Default constructor for the FeedbackAndReviewsSteps class.
     * Initializes the FeedbackAndReviews and Program objects for use in the step definitions.
     */
    public FeedbackAndReviewsSteps() {
        // The constructor could be empty, but still needs a comment to avoid the warning.
    }
    /**
     * This step is invoked when the user completes a program.
     *
     * @param programName The name of the program that the user has completed.
     */
    @Given("the user has completed the {string} program")
    public void theUserHasCompletedTheProgram(String programName) {
        program = new Program(programName);
        feedbackAndReviews = new FeedbackAndReviews(program);
    }

    /**
     * This step is invoked when the user rates the program with a score.
     *
     * @param rating The rating given by the user for the program.
     */
    @When("the user rates the program with a score of {string}")
    public void theUserRatesTheProgramWithAScoreOf(String rating) {
        feedbackAndReviews.addRating(rating);
    }

    /**
     * This step is invoked when the user writes a review for the program.
     *
     * @param reviewText The review text written by the user.
     */
    @When("the user writes a review saying {string}")
    public void theUserWritesAReviewSaying(String reviewText) {
        feedbackAndReviews.addReview(reviewText);
    }

    /**
     * This step is used to check if the program has a review with the specified details.
     *
     * @param dataTable A DataTable containing the expected review and rating information.
     */
    @Then("the program should have a review with:")
    public void theProgramShouldHaveAReviewWith(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String expectedRating = rows.get(1).get(0);
        String expectedReview = rows.get(1).get(1);

        assertEquals(expectedRating, feedbackAndReviews.getRating());
        assertEquals(expectedReview, feedbackAndReviews.getReview());
    }

    /**
     * This step is invoked when the user submits a suggestion for the program.
     *
     * @param suggestionText The suggestion written by the user.
     */
    @When("the user submits a suggestion saying {string}")
    public void theUserSubmitsASuggestionSaying(String suggestionText) {
        feedbackAndReviews.submitSuggestion(suggestionText);
    }

    /**
     * This step is used to verify that the instructor receives the suggestion.
     *
     * @param dataTable A DataTable containing the expected suggestion content.
     */
    @Then("the instructor should receive the suggestion with the following content:")
    public void theInstructorShouldReceiveTheSuggestionWithTheFollowingContent(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String expectedSuggestion = rows.get(1).get(0);

        assertEquals(expectedSuggestion, feedbackAndReviews.getSuggestion());
    }


    /**
     * Represents a program that the user can give feedback on.
     */
    public class Program {
        private String name;
        private String rating;
        private String review;
        private String suggestion;

        /**
         * Constructs a Program object with the specified program name.
         *
         * @param name The name of the program.
         */
        public Program(String name) {
            this.name = name;
        }

        /**
         * Gets the name of the program.
         *
         * @return The name of the program.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the rating for the program.
         *
         * @param rating The rating to be set.
         */
        public void setRating(String rating) {
            this.rating = rating;
        }

        /**
         * Sets the review for the program.
         *
         * @param review The review text to be set.
         */
        public void setReview(String review) {
            this.review = review;
        }

        /**
         * Sets the suggestion for the program.
         *
         * @param suggestion The suggestion text to be set.
         */
        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        /**
         * Gets the rating of the program.
         *
         * @return The rating of the program.
         */
        public String getRating() {
            return rating;
        }

        /**
         * Gets the review of the program.
         *
         * @return The review of the program.
         */
        public String getReview() {
            return review;
        }

        /**
         * Gets the suggestion for the program.
         *
         * @return The suggestion for the program.
         */
        public String getSuggestion() {
            return suggestion;
        }
    }

    /**
     * Manages the feedback and reviews for a given program.
     */
    public class FeedbackAndReviews {
        private Program program;

        /**
         * Constructs a FeedbackAndReviews object for a specific program.
         *
         * @param program The program associated with this feedback system.
         */
        public FeedbackAndReviews(Program program) {
            this.program = program;
        }

        /**
         * Adds a rating for the program.
         *
         * @param rating The rating to be added to the program.
         */
        public void addRating(String rating) {
            program.setRating(rating);
        }

        /**
         * Adds a review for the program.
         *
         * @param review The review text to be added to the program.
         */
        public void addReview(String review) {
            program.setReview(review);
        }

        /**
         * Submits a suggestion for the program.
         *
         * @param suggestion The suggestion to be submitted for the program.
         */
        public void submitSuggestion(String suggestion) {
            program.setSuggestion(suggestion);
        }

        /**
         * Gets the rating for the program.
         *
         * @return The rating for the program.
         */
        public String getRating() {
            return program.getRating();
        }

        /**
         * Gets the review for the program.
         *
         * @return The review for the program.
         */
        public String getReview() {
            return program.getReview();
        }

        /**
         * Gets the suggestion for the program.
         *
         * @return The suggestion for the program.
         */
        public String getSuggestion() {
            return program.getSuggestion();
        }
        }
}
