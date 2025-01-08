package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains step definitions for the Content Management feature in the
 * Cucumber-based testing framework. It handles articles and feedback statuses.
 */
public class ContentManagementSteps {

    // Maps to hold articles and feedbacks for testing
    private final Map<String, Article> articles = new HashMap<>();
    private final Map<String, Feedback> feedbacks = new HashMap<>();
    /**
     * Default constructor for ContentManagementSteps.
     * Initializes the maps for articles and feedbacks.
     */
    public ContentManagementSteps() {
        // Explicit default constructor (even though not necessary)
    }
    /**
     * A static class representing an Article.
     */
    static class Article {
        String title;
        String type;
        String status;

        /**
         * Constructor for the Article class.
         *
         * @param title The title of the article.
         * @param type The type of article.
         * @param status The current status of the article.
         */
        Article(String title, String type, String status) {
            this.title = title;
            this.type = type;
            this.status = status;
        }
    }

    /**
     * A static class representing Feedback.
     */
    static class Feedback {
        String user;
        String message;
        String status;

        /**
         * Constructor for the Feedback class.
         *
         * @param user The user who gave the feedback.
         * @param message The feedback message.
         * @param status The current status of the feedback.
         */
        Feedback(String user, String message, String status) {
            this.user = user;
            this.message = message;
            this.status = status;
        }
    }

    /**
     * Given step to initialize articles pending approval from a Cucumber DataTable.
     *
     * @param dataTable The table containing article details.
     */
    @Given("the following articles are pending approval:")
    public void theFollowingArticlesArePendingApproval(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String title = row.get("Title");
            String type = row.get("Type");
            String status = row.get("Status");
            assertNotNull(title, "Article title cannot be null");
            assertNotNull(type, "Article type cannot be null");
            assertNotNull(status, "Article status cannot be null");
            articles.put(title, new Article(title, type, status));
        });
    }

    /**
     * When step to approve an article by title.
     *
     * @param title The title of the article to approve.
     */
    @When("I approve the article {string}")
    public void iApproveTheArticle(String title) {
        Article article = articles.get(title);
        if (article != null) {
            if ("Pending".equals(article.status)) {
                article.status = "Approved";
            } else {
                fail("Article status must be 'Pending' to approve. Current status: " + article.status);
            }
        } else {
            fail("Article not found: " + title);
        }
    }

    /**
     * When step to reject an article by title.
     *
     * @param title The title of the article to reject.
     */
    @When("I reject the article {string}")
    public void iRejectTheArticle(String title) {
        Article article = articles.get(title);
        if (article != null) {
            if ("Pending".equals(article.status)) {
                article.status = "Rejected";
            } else {
                fail("Article status must be 'Pending' to reject. Current status: " + article.status);
            }
        } else {
            fail("Article not found: " + title);
        }
    }

    /**
     * Then step to verify the status of an article after an action.
     *
     * @param title The title of the article.
     * @param expectedStatus The expected status of the article.
     */
    @Then("the status of the article {string} should be {string}")
    public void theStatusOfTheArticleShouldBe(String title, String expectedStatus) {
        Article article = articles.get(title);
        assertNotNull(article, "Article not found: " + title);
        assertEquals(expectedStatus, article.status, "Status mismatch for article: " + title);
    }

    /**
     * Given step to initialize existing feedback from a Cucumber DataTable.
     *
     * @param dataTable The table containing feedback details.
     */
    @Given("the following feedbacks exist:")
    public void theFollowingFeedbacksExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String user = row.get("User");
            String message = row.get("Message");
            String status = row.get("Status");
            assertNotNull(user, "Feedback user cannot be null");
            assertNotNull(message, "Feedback message cannot be null");
            assertNotNull(status, "Feedback status cannot be null");
            feedbacks.put(user, new Feedback(user, message, status));
        });
    }

    /**
     * When step to resolve feedback from a specific user.
     *
     * @param user The user whose feedback is to be resolved.
     */
    @When("I resolve the feedback from {string}")
    public void iResolveTheFeedbackFrom(String user) {
        Feedback feedback = feedbacks.get(user);
        if (feedback != null) {
            if ("Open".equals(feedback.status)) {
                feedback.status = "Resolved";
            } else {
                fail("Feedback status must be 'Open' to resolve. Current status: " + feedback.status);
            }
        } else {
            fail("Feedback not found for user: " + user);
        }
    }

    /**
     * Then step to verify the status of feedback from a user after an action.
     *
     * @param user The user whose feedback status is to be checked.
     * @param expectedStatus The expected status of the feedback.
     */
    @Then("the status of the feedback from {string} should be {string}")
    public void theStatusOfTheFeedbackFromShouldBe(String user, String expectedStatus) {
        Feedback feedback = feedbacks.get(user);
        assertNotNull(feedback, "Feedback not found for user: " + user);
        assertEquals(expectedStatus, feedback.status, "Status mismatch for feedback from user: " + user);
    }

    /**
     * When step to attempt approving a non-existing article by title.
     *
     * @param title The title of the article to approve.
     */
    @When("I attempt to approve a non-existing article {string}")
    public void iAttemptToApproveANonExistingArticle(String title) {
        Article article = articles.get(title);
        assertNull(article, "Expected article not to exist: " + title);
    }

    /**
     * When step to attempt rejecting a non-existing article by title.
     *
     * @param title The title of the article to reject.
     */
    @When("I attempt to reject a non-existing article {string}")
    public void iAttemptToRejectANonExistingArticle(String title) {
        Article article = articles.get(title);
        if (article == null) {
            fail("Cannot reject an article that does not exist: " + title);
        } else {
            fail("Article should not exist for rejection: " + title);
        }
    }

    /**
     * When step to attempt resolving a non-existing feedback by user.
     *
     * @param user The user whose feedback is to be resolved.
     */
    @When("I attempt to resolve a non-existing feedback from {string}")
    public void iAttemptToResolveANonExistingFeedback(String user) {
        Feedback feedback = feedbacks.get(user);
        assertNull(feedback, "Expected feedback not to exist for user: " + user);
    }
}
