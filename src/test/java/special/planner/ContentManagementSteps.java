package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ContentManagementSteps {

    private final Map<String, Article> articles = new HashMap<>();
    private final Map<String, Feedback> feedbacks = new HashMap<>();

    static class Article {
        String title;
        String type;
        String status;

        Article(String title, String type, String status) {
            this.title = title;
            this.type = type;
            this.status = status;
        }
    }

    static class Feedback {
        String user;
        String message;
        String status;

        Feedback(String user, String message, String status) {
            this.user = user;
            this.message = message;
            this.status = status;
        }
    }

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

    @Then("the status of the article {string} should be {string}")
    public void theStatusOfTheArticleShouldBe(String title, String expectedStatus) {
        Article article = articles.get(title);
        assertNotNull(article, "Article not found: " + title);
        assertEquals(expectedStatus, article.status, "Status mismatch for article: " + title);
    }

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

    @Then("the status of the feedback from {string} should be {string}")
    public void theStatusOfTheFeedbackFromShouldBe(String user, String expectedStatus) {
        Feedback feedback = feedbacks.get(user);
        assertNotNull(feedback, "Feedback not found for user: " + user);
        assertEquals(expectedStatus, feedback.status, "Status mismatch for feedback from user: " + user);
    }

    @When("I attempt to approve a non-existing article {string}")
    public void iAttemptToApproveANonExistingArticle(String title) {
        Article article = articles.get(title);
        assertNull(article, "Expected article not to exist: " + title);
    }

    @When("I attempt to reject a non-existing article {string}")
    public void iAttemptToRejectANonExistingArticle(String title) {
        Article article = articles.get(title);
        if (article == null) {

            fail("Cannot reject an article that does not exist: " + title);
        } else {

            fail("Article should not exist for rejection: " + title);
        }
    }
    @When("I attempt to resolve a non-existing feedback from {string}")
    public void iAttemptToResolveANonExistingFeedback(String user) {
        Feedback feedback = feedbacks.get(user);
        assertNull(feedback, "Expected feedback not to exist for user: " + user);
    }
}
