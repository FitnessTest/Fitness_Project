package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SubscriptionManagement {
    private static final String USER_ID_PREFIX = "User with ID ";
    private static final Logger logger = Logger.getLogger(SubscriptionManagement.class.getName());

    public enum Plan {
        BASIC,
        PREMIUM
    }

    public enum UserType {
        CLIENT,
        INSTRUCTOR
    }

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

    private static Map<String, User> userSubscriptions;

    public SubscriptionManagement() {
        userSubscriptions = new HashMap<>();

        userSubscriptions.put("1", new User("1", Plan.BASIC, UserType.CLIENT));
        userSubscriptions.put("2", new User("2", Plan.PREMIUM, UserType.INSTRUCTOR));
        userSubscriptions.put("3", new User("3", Plan.PREMIUM, UserType.CLIENT));
    }

    public Plan getSubscriptionPlan(String id) {
        User user = userSubscriptions.get(id);
        return (user != null) ? user.getPlan() : Plan.BASIC;
    }

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
            logger.warning("No user found with ID " + id);
        }
    }

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
            logger.warning("No user found with ID " + id);
        }
    }

    public static void viewAllSubscriptions() {
        if (userSubscriptions == null || userSubscriptions.isEmpty()) {
            logger.info("No users have subscriptions.");
        } else {
            logger.info("All user subscriptions:");
            logger.info("+-------+------------+------------+");
            logger.info(String.format("| %-5s | %-10s | %-10s |", "ID", "Plan", "Type"));
            logger.info("+-------+------------+------------+");
            for (User user : userSubscriptions.values()) {
                logger.info(String.format("| %-5s | %-10s | %-10s |", user.getId(), user.getPlan(), user.getType()));
            }
            logger.info("+-------+------------+------------+");
        }
    }

    public static void changeSubscriptionPlan(String id, Plan newPlan) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            Plan oldPlan = user.getPlan();
            user.setPlan(newPlan);
            logger.info(USER_ID_PREFIX  + id + " subscription plan changed from " + oldPlan + " to " + newPlan);
        } else {
            logger.warning("No user found with ID " + id);
        }
    }
}
