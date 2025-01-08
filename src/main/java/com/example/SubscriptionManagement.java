package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Manages the subscription plans for users including upgrading, downgrading, and viewing subscriptions.
 */
public class SubscriptionManagement {
    private static final String USER_ID_PREFIX = "User with ID ";
    private static final String USER_NOT_FOUND_MESSAGE = "No user found with ID ";
    private static final String TABLE_BORDER = "+-------+------------+------------+";
    private static final Logger logger = Logger.getLogger(SubscriptionManagement.class.getName());

    /**
     * Enum representing the subscription plans.
     */
    public enum Plan {
        /**
         * Basic subscription plan.
         */
        BASIC,

        /**
         * Premium subscription plan.
         */
        PREMIUM // Premium subscription plan
    }

    /**
     * Enum representing the types of users.
     */
    public enum UserType {
        /**
         * Client type user.
         */
        CLIENT,

        /**
         * Instructor type user.
         */
        INSTRUCTOR // Instructor type user
    }
    /**
     * A static map that stores user subscriptions, keyed by their ID.
     */
    public static Map<String, User> userSubscriptions;

    /**
     * Represents a user with an ID, subscription plan, and user type.
     */
    private static class User {
        private String id;
        private Plan plan;
        private UserType type;

        public User(String id, Plan plan, UserType type) {
            this.id = id;
            this.plan = plan;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public UserType getType() {
            return type;
        }
    }

    /**
     * Initializes user subscriptions.
     */
    public SubscriptionManagement() {
        userSubscriptions = new HashMap<>();
        userSubscriptions.put("1", new User("1", Plan.BASIC, UserType.CLIENT));
        userSubscriptions.put("2", new User("2", Plan.PREMIUM, UserType.INSTRUCTOR));
        userSubscriptions.put("3", new User("3", Plan.PREMIUM, UserType.CLIENT));
    }

    /**
     * Retrieves the subscription plan for a user by their ID.
     *
     * @param id the user ID
     * @return the user's subscription plan
     */
    public Plan getSubscriptionPlan(String id) {
        User user = userSubscriptions.get(id);
        return (user != null) ? user.getPlan() : Plan.BASIC;
    }

    /**
     * Upgrades a user's subscription to the PREMIUM plan.
     *
     * @param id the user ID
     */
    public static void upgradeToPremium(String id) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            if (user.getPlan() == Plan.PREMIUM) {
                logger.info(USER_ID_PREFIX  + id + " is already on Premium.");
            } else {
                user.setPlan(Plan.PREMIUM);
                logger.info(USER_ID_PREFIX  + id + " upgraded to Premium.");
            }
        } else {
            logger.warning(USER_NOT_FOUND_MESSAGE + id);
        }
    }

    /**
     * Downgrades a user's subscription to the BASIC plan.
     *
     * @param id the user ID
     */
    public static void downgradeToBasic(String id) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            if (user.getPlan() == Plan.BASIC) {
                logger.info(USER_ID_PREFIX  + id + " is already on Basic.");
            } else {
                user.setPlan(Plan.BASIC);
                logger.info(USER_ID_PREFIX  + id + " downgraded to Basic.");
            }
        } else {
            logger.warning(USER_NOT_FOUND_MESSAGE + id);
        }
    }

    /**
     * Displays all user subscriptions.
     */
    public static void viewAllSubscriptions() {
        if (userSubscriptions == null || userSubscriptions.isEmpty()) {
            logger.info("No users have subscriptions.");
        } else {
            logger.info("All user subscriptions:");
            logger.info(TABLE_BORDER);
            logger.info(String.format("| %-5s | %-10s | %-10s |", "ID", "Plan", "Type"));
            logger.info(TABLE_BORDER);
            for (User user : userSubscriptions.values()) {
                logger.info(String.format("| %-5s | %-10s | %-10s |", user.getId(), user.getPlan(), user.getType()));
            }
            logger.info(TABLE_BORDER);
        }
    }

    /**
     * Changes a user's subscription plan.
     *
     * @param id the user ID
     * @param newPlan the new plan to switch to
     */
    public static void changeSubscriptionPlan(String id, Plan newPlan) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            Plan oldPlan = user.getPlan();
            user.setPlan(newPlan);
            logger.info(USER_ID_PREFIX  + id + " subscription plan changed from " + oldPlan + " to " + newPlan);
        } else {
            logger.warning(USER_NOT_FOUND_MESSAGE + id);
        }
    }
}
