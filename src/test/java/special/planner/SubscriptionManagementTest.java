package special.planner;

import com.example.SubscriptionManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SubscriptionManagement class. This class tests the functionality of
 * subscription plan management, including getting, upgrading, downgrading, and changing
 * subscription plans for users.
 */
public class SubscriptionManagementTest {

    private SubscriptionManagement subscriptionManagement;
    private static final Logger logger = Logger.getLogger(SubscriptionManagement.class.getName());

    /**
     * Default constructor for SubscriptionManagementTest.
     * This constructor is implicitly called by the JUnit testing framework.
     */
    public SubscriptionManagementTest() {
        // Optional: You can add any initialization if necessary.
        // JUnit 5 typically manages the creation of test instances.
    }

    /**
     * Setup method to initialize the SubscriptionManagement instance and configure the logger
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        subscriptionManagement = new SubscriptionManagement();

        // Setting up the console logger to output all log messages
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    /**
     * Test the functionality of getting the current subscription plan for a user.
     */
    @Test
    public void testGetSubscriptionPlan() {
        // Test that the subscription plans are returned correctly
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("1"));
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("2"));
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("3"));
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    /**
     * Test the functionality of upgrading a user's subscription to Premium.
     */
    @Test
    public void testUpgradeToPremium() {
        // Test upgrading users to Premium
        SubscriptionManagement.upgradeToPremium("1");
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("1"));

        SubscriptionManagement.upgradeToPremium("2");
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("2"));

        // Test upgrading a nonexistent user, should remain Basic
        SubscriptionManagement.upgradeToPremium("nonexistent");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    /**
     * Test the functionality of downgrading a user's subscription to Basic.
     */
    @Test
    public void testDowngradeToBasic() {
        // Test downgrading users to Basic
        SubscriptionManagement.downgradeToBasic("2");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("2"));

        SubscriptionManagement.downgradeToBasic("1");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("1"));

        // Test downgrading a nonexistent user, should remain Basic
        SubscriptionManagement.downgradeToBasic("nonexistent");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    /**
     * Test the functionality of viewing all subscriptions.
     * This might require assertions based on logging output or internal state.
     */
    @Test
    public void testViewAllSubscriptions() {
        assertNotNull(subscriptionManagement);
        assertFalse(SubscriptionManagement.userSubscriptions.isEmpty());

        // Test the viewAllSubscriptions method (you might need to assert against log output)
        subscriptionManagement.viewAllSubscriptions();
        // You might want to add assertions here to inspect log output or internal state
    }

    /**
     * Test the functionality of changing a user's subscription plan.
     */
    @Test
    public void testChangeSubscriptionPlan() {
        // Test changing subscription plans for users
        SubscriptionManagement.changeSubscriptionPlan("1", SubscriptionManagement.Plan.PREMIUM);
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("1"));

        SubscriptionManagement.changeSubscriptionPlan("2", SubscriptionManagement.Plan.BASIC);
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("2"));

        // Test changing subscription plan for a nonexistent user, should default to BASIC
        SubscriptionManagement.changeSubscriptionPlan("nonexistent", SubscriptionManagement.Plan.PREMIUM);
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }
}
