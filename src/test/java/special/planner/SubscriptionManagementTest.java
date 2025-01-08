package special.planner;

import com.example.SubscriptionManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionManagementTest {

    private SubscriptionManagement subscriptionManagement;
    private static final Logger logger = Logger.getLogger(SubscriptionManagement.class.getName());

    @BeforeEach
    public void setUp() {
        subscriptionManagement = new SubscriptionManagement();

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    @Test
    public void testGetSubscriptionPlan() {
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("1"));
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("2"));
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("3"));
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    @Test
    public void testUpgradeToPremium() {
        SubscriptionManagement.upgradeToPremium("1");
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("1"));

        SubscriptionManagement.upgradeToPremium("2");
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("2"));

        SubscriptionManagement.upgradeToPremium("nonexistent");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    @Test
    public void testDowngradeToBasic() {
        SubscriptionManagement.downgradeToBasic("2");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("2"));

        SubscriptionManagement.downgradeToBasic("1");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("1"));

        SubscriptionManagement.downgradeToBasic("nonexistent");
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }

    @Test
    public void testViewAllSubscriptions() {
        assertNotNull(subscriptionManagement);
        assertFalse(SubscriptionManagement.userSubscriptions.isEmpty());

        subscriptionManagement.viewAllSubscriptions();
        // You might want to add assertions that inspect the log output or the expected state of userSubscriptions.
    }
    @Test
    public void testChangeSubscriptionPlan() {
        SubscriptionManagement.changeSubscriptionPlan("1", SubscriptionManagement.Plan.PREMIUM);
        assertEquals(SubscriptionManagement.Plan.PREMIUM, subscriptionManagement.getSubscriptionPlan("1"));

        SubscriptionManagement.changeSubscriptionPlan("2", SubscriptionManagement.Plan.BASIC);
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("2"));

        SubscriptionManagement.changeSubscriptionPlan("nonexistent", SubscriptionManagement.Plan.PREMIUM);
        assertEquals(SubscriptionManagement.Plan.BASIC, subscriptionManagement.getSubscriptionPlan("nonexistent"));
    }
}
