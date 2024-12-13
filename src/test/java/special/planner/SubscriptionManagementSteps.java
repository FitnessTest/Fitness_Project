package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionManagementSteps {

    private final Map<String, Subscription> subscriptions = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    static class Subscription {
        String planName;
        String type;
        double price;

        Subscription(String planName, String type, double price) {
            this.planName = planName;
            this.type = type;
            this.price = price;
        }
    }

    static class User {
        private String subscriptionPlan;

        User(String subscriptionPlan) {
            this.subscriptionPlan = subscriptionPlan;
        }

        public String getSubscriptionPlan() {
            return subscriptionPlan;
        }

        public void setSubscriptionPlan(String subscriptionPlan) {
            this.subscriptionPlan = subscriptionPlan;
        }
    }

    @Given("the following subscription plans exist:")
    public void theFollowingSubscriptionPlansExist(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String planName = row.get("Plan Name").trim();
            String type = row.get("Type").trim();
            double price = Double.parseDouble(row.get("Price").trim());
            subscriptions.put(planName, new Subscription(planName, type, price));
        });
    }

    @When("I add a new subscription plan with the following details:")
    public void iAddANewSubscriptionPlanWithTheFollowingDetails(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String planName = row.get("Plan Name").trim();
            String type = row.get("Type").trim();
            double price = Double.parseDouble(row.get("Price").trim());
            subscriptions.put(planName, new Subscription(planName, type, price));
        });
    }

    @Then("the subscription plan {string} should exist with price {double}")
    public void theSubscriptionPlanShouldExistWithPrice(String planName, double expectedPrice) {
        assertTrue(subscriptions.containsKey(planName.trim()));
        assertEquals(expectedPrice, subscriptions.get(planName.trim()).price);
    }

    @When("I update the price of the subscription plan {string} to {double}")
    public void iUpdateThePriceOfTheSubscriptionPlan(String planName, double newPrice) {
        Subscription subscription = subscriptions.get(planName.trim());
        if (subscription != null) {
            subscription.price = newPrice;
        }
    }

    @Then("the price of the subscription plan {string} should be {double}")
    public void thePriceOfTheSubscriptionPlanShouldBe(String planName, double expectedPrice) {
        assertEquals(expectedPrice, subscriptions.get(planName.trim()).price);
    }

    @When("I remove the subscription plan {string}")
    public void iRemoveTheSubscriptionPlan(String planName) {
        subscriptions.remove(planName.trim());
    }

    @Then("the subscription plan {string} should not exist")
    public void theSubscriptionPlanShouldNotExist(String planName) {
        assertFalse(subscriptions.containsKey(planName.trim()));
    }

    @When("I assign a {string} subscription plan to the following user:")
    public void iAssignASubscriptionPlanToTheFollowingUser(String subscriptionPlan, DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            String name;
            name = row.get("Name").trim();
            row.get("Role");
            users.put(name, new User(subscriptionPlan.trim()));
        }
    }

    @Then("the user {string} should have the {string} subscription plan")
    public void theUserShouldHaveTheSubscriptionPlan(String userName, String expectedPlan) {
        userName = userName.trim();
        assertTrue(users.containsKey(userName), "User not found: " + userName);
        assertEquals(expectedPlan.trim(), users.get(userName).getSubscriptionPlan());
    }

    @When("I upgrade the subscription plan of {string} to {string}")
    public void iUpgradeTheSubscriptionPlanOfTo(String userName, String newPlan) {
        User user = users.get(userName.trim());
        if (user != null) {
            user.setSubscriptionPlan(newPlan.trim());
        }
    }

    @Then("the subscription plan of {string} should be {string}")
    public void theSubscriptionPlanOfShouldBe() {

    }

    @When("I downgrade the subscription plan of {string} to {string}")
    public void iDowngradeTheSubscriptionPlanOfTo(String userName, String newPlan) {
        User user = users.get(userName.trim());
        if (user != null) {
            user.setSubscriptionPlan(newPlan.trim());
        }
    }
}
