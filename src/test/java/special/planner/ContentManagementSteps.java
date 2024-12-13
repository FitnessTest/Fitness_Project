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
            articles.put(title, new Article(title, type, status));
        });
    }

    @When("I approve the article {string}")
    public void iApproveTheArticle(String title) {
        Article article = articles.get(title);
        if (article != null && article.status.equals("Pending")) {
            article.status = "Approved";
        }
    }

    @When("I reject the article {string}")
    public void iRejectTheArticle(String title) {
        Article article = articles.get(title);
        if (article != null && article.status.equals("Pending")) {
            article.status = "Rejected";
        }
    }

    @Then("the status of the article {string} should be {string}")
    public void theStatusOfTheArticleShouldBe(String title, String expectedStatus) {
        assertEquals(expectedStatus, articles.get(title).status);
    }

    @Given("the following feedbacks exist:")
    public void theFollowingFeedbacksExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String user = row.get("User");
            String message = row.get("Message");
            String status = row.get("Status");
            feedbacks.put(user, new Feedback(user, message, status));
        });
    }

    @When("I resolve the feedback from {string}")
    public void iResolveTheFeedbackFrom(String user) {
        Feedback feedback = feedbacks.get(user);
        if (feedback != null && feedback.status.equals("Open")) {
            feedback.status = "Resolved";
        }
    }

    @Then("the status of the feedback from {string} should be {string}")
    public void theStatusOfTheFeedbackFromShouldBe(String user, String expectedStatus) {
        assertEquals(expectedStatus, feedbacks.get(user).status);
    }
}
